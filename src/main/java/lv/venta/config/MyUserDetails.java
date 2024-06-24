package lv.venta.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lv.venta.model.Dalibnieks;


public class MyUserDetails implements UserDetails{

	
	private Dalibnieks dalibnieks;
	
	public MyUserDetails(Dalibnieks dalibnieks) {
		this.dalibnieks = dalibnieks;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(dalibnieks.getLoma()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return dalibnieks.getParole();
	}

	@Override
	public String getUsername() {
		return dalibnieks.getLietotajvards();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}