package in.venky.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.venky.Service.CustmorService;

@Configuration
@EnableWebSecurity
public class AppSecurity {
	
	@Autowired
	private CustmorService cs;
	
	@Bean
	public PasswordEncoder pwdencoder() {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider autprovider() {
		DaoAuthenticationProvider dp=new DaoAuthenticationProvider();
		dp.setUserDetailsService(cs);
		dp.setPasswordEncoder(pwdencoder());
		return dp;
	}
	@Bean
	public AuthenticationManager authencation(AuthenticationConfiguration ac)throws Exception {
		
		return ac.getAuthenticationManager();
		
	}
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
				return http.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers("/insert","/login")
				
				.permitAll()
				.and()
				.build();
				
 }
}
