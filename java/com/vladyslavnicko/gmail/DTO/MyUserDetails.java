package com.vladyslavnicko.gmail.DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vladyslavnicko.gmail.model.Role;
import com.vladyslavnicko.gmail.model.User;

public class MyUserDetails implements UserDetails{
	
	private User user;
    
    public MyUserDetails(User user) {
        this.user = user;
    }
    
    public User getUser() {
    	return user;
    }
    
    public long getUserId() {
    	return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	Role role = user.getRole();
    	List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    	authorities.add(new SimpleGrantedAuthority(role.name()));

    	return authorities;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
