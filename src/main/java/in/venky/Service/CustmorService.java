package in.venky.Service;

import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.venky.Entity.Custmor;
import in.venky.Repo.CustmorRepo;

@Service
public class CustmorService implements UserDetailsService {
	
	
	@Autowired
	private CustmorRepo cr;

	@Override
	public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
	Custmor custmor = cr.findByEmail(Email);
	
	return new User(custmor.getEmail(),custmor.getPwd(),Collections.emptyList());
	
	}

}
