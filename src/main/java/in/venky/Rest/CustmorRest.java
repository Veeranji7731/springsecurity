package in.venky.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.venky.Entity.Custmor;
import in.venky.Repo.CustmorRepo;

@RestController
public class CustmorRest {
	
	@Autowired
	private CustmorRepo cr;
	@Autowired
	private PasswordEncoder pe;
	@Autowired
	private AuthenticationManager am;
	
	@PostMapping("/login")
	public ResponseEntity<String> logincheck(@RequestBody Custmor c){
		UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPwd());
		try {
			
		Authentication authenticate = am.authenticate(token);
		if(authenticate.isAuthenticated()) {
			return new ResponseEntity<String>("sucess login",HttpStatus.OK);
			
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
			return new ResponseEntity<String>("invaild ",HttpStatus.UNAUTHORIZED);
		}
		
		
	
		@PostMapping("/insert")
	public ResponseEntity<String> welcome(@RequestBody Custmor c){
		String s = pe.encode(c.getPwd());
		c.setPwd(s);
	    cr.save(c);
		return new ResponseEntity<String>("sucess",HttpStatus.CREATED);
				
	}
	
 
}
