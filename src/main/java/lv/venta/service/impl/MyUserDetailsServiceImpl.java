package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.venta.config.MyUserDetails;
import lv.venta.model.Dalibnieks;
import lv.venta.repo.IDalibnieksRepo;


@Service
public class MyUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IDalibnieksRepo dalibnieksRepo;
	
	@Override
	public UserDetails loadUserByUsername(String lietotajvards) throws UsernameNotFoundException {
		Dalibnieks dalibnieks = dalibnieksRepo.findByLietotajvards(lietotajvards);
		
		if(dalibnieks == null) throw new UsernameNotFoundException(lietotajvards + " nav atrasts");
		
		MyUserDetails userDet = new MyUserDetails(dalibnieks);
		return userDet;
	}

}