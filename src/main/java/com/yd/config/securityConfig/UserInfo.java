package com.yd.config.securityConfig;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用来保存用户账户，密码，权限等，
 * @author FelixChen
 *
 */
public class UserInfo implements Serializable, UserDetails {
      private static final long serialVersionUID = 1L;
      
      private String username;
      private String password;
      
      private String role;//多个权限，使用","隔开，getAuthorities()里的方法会转换成list
      
      private boolean accountNonExpired;//是否过期
      private boolean accountNonLocked;//是否锁定
      private boolean credentialsNonExpired;//是否认证过期
      private boolean enabled;
      public UserInfo(String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked,
                  boolean credentialsNonExpired, boolean enabled) {
            this.username = username;
            this.password = password;
            this.role = role;
            this.accountNonExpired = accountNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.credentialsNonExpired = credentialsNonExpired;
            this.enabled = enabled;
      }
	public UserInfo() {
		super();
	}


	// 这是权限
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
      }
      @Override
      public String getPassword() {
            return password;
      }
      @Override
      public String getUsername() {
            return username;
      }
      @Override
      public boolean isAccountNonExpired() {
            return accountNonExpired;
      }
      @Override
      public boolean isAccountNonLocked() {
            return accountNonLocked;
      }
      @Override
      public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
      }
      @Override
      public boolean isEnabled() {
            return enabled;
      }

      
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public int hashCode() {
		System.out.println("hashCode");
		 return username.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		if(obj.toString().equals(this.username))
            return true;
        return false;
	}
	@Override
	public String toString() {
		 return this.username;
	}
	
      
}