package com.yd.config.securityConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.yd.config.exception.CommonException;
import com.yd.entity.vo.ResourceVO;
import com.yd.mapper.RoleResourceMapper;
import com.yd.service.RedisService;
import com.yd.utils.JsonUtils;

/**
 * 返回权限验证的接口
 * 
 *
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	@Autowired
	private  RedisService  redisService;
	
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		String urlRecord = "";
		
		if (principal instanceof UserDetails) { // 首先判断先当前用户是否是我们UserDetails对象。
			UserDetails  userDetails = (UserDetails) principal;
			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();// 得到该用户所有权限
			// 把权限封装到list中
			List<String> roleList = new ArrayList<>();
			for (GrantedAuthority grantedAuthority : authorities) {
				roleList.add(grantedAuthority.getAuthority());
			}
			//权限对应的资源url集合
			List<ResourceVO>   urlList = null;
			
			urlList = redisService.getList(userDetails.getUsername(), ResourceVO.class);
			
			if(urlList == null || urlList.size() == 0){
				if (roleList.size() > 0) {
					// 根据权限名称查对应的资源(tbrole_resource)
					urlList = roleResourceMapper.selectRoleUrlByRoleNames(roleList);
					//保存到redis缓存中，否则每次请求都会访问数据库,设置过期时间30分
					//应该用户退出就清空redis缓存的，但是由于退出方法是security内部方法实现的，所以还要继续改进，这里就不做处理了
					redisService.set(userDetails.getUsername(), JsonUtils.objectToJson(urlList),30,TimeUnit.MINUTES);
				}
			}
			

			if(urlList != null  && urlList.size() > 0){
				
				// Set<String> urls = new TreeSet<>(); //
				// 数据库读取，读取用户所拥有权限的所有URL,因为一个用户可能有多个角色，多个角色可能url重复，所以用set
				List<String> urls = new ArrayList<String>(urlList.size());// 因为数据库去重了，所以还是list。习惯

				for (ResourceVO re : urlList) {
					urls.add(re.getUrl());
				}
				
				
				urlRecord = request.getRequestURI();
				// 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
				for (String url : urls) {
					if (antPathMatcher.match(url, urlRecord)) {
						hasPermission = true;
						break;
					}
				}
			}
		}
		
		return  hasPermission;
//		if(hasPermission){
//			return  hasPermission;
//		}else {
//			 throw new AccessDeniedException("权限不足");  //AccessDeniedException报错，会跳到登录页面,
//		}
	}
}