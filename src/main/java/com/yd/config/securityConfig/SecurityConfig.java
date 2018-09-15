package com.yd.config.securityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * 
 * @author FelixChen
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private  AuthenticationProvider  authenticationProvider;
	@Autowired
	@Qualifier("myAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	@Qualifier("myAuthenticationFailHander")
    private AuthenticationFailureHandler myAuthenticationFailHander;
	@Autowired
	UserDetailsService userDetailsService;  
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//登出
		http.logout()
        .logoutUrl("/manager/logout")//设置登出处理的url  
        .logoutSuccessUrl("/manager/index");//设置登出成功后跳转到首页，默认是跳转到登录页面  
        
		
		
		////表单登录，permitAll()表示这个不需要验证登录页面，登录请求，否则会报重定向次数太多
		//loginPage:自定义登陆页面(可以直接指定静态文件或通过接口跳转页面)
		//loginProcessingUrl:自定义登陆请求url，默认是security定义的login,在静态文件action中填写这里配置的
		http.formLogin().loginPage("/login.html").loginProcessingUrl("/manager/login")
		.successHandler(myAuthenticationSuccessHandler)//登录成功后的操作
//		.failureUrl("/manager/error")
		.failureHandler(myAuthenticationFailHander)//登录失败之后的操作
		.permitAll();
		
              
		//首页不拦截，必须全路径(无权限会跳到/error/{code})
		http.authorizeRequests().antMatchers("/manager/index","/error/**").permitAll()
		//静态资源不拦截，默认查找classpath下的，详情请看笔记web配置Thymeleaf和静态文件
		.and().authorizeRequests().antMatchers(new String[]{"*.js","*.css","/img/**","/images/**","/fonts/**","/**/favicon.ico","/**/*.html","/druid/**",}).permitAll()
		.and().authorizeRequests().anyRequest().access("@rbacService.hasPermission(request,authentication)")//必须经过认证才可以访问，自定义类
        .and()
        .csrf().disable();
		
		//以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次,UserDetails实现类必须要重写hashCode,equals,toString三个方法
        http.sessionManagement().maximumSessions(1).expiredUrl("/login.html");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider);
		 //不删除凭据，以便记住用户
        auth.eraseCredentials(false);
//		 auth
//         .inMemoryAuthentication()
//         		//自定义账号和密码，默认账号密码不起作用了
//               .withUser("admin").password("123456").roles("USER")
//               .and()
//               .withUser("yd").password("123").roles("ADMIN");
	}
	
	 
	@Autowired
	private  DataSource  dataSource;
	/**
     * 记住我功能的token存取器配置，好像没作用
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
          JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
          tokenRepository.setDataSource(dataSource);
          return tokenRepository;
    }
}
