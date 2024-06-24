package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lv.venta.service.impl.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig{
		
	@Bean
	public MyUserDetailsServiceImpl userDetailsManager() {
		return new MyUserDetailsServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider linkWithDB() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsManager());
		provider.setPasswordEncoder(encoder);	
		return provider;
	}
	
	@Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
	
	/*@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form->form.loginPage("/login").permitAll())
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .build();
    }*/
	
	/*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
            	.logoutUrl("/logout")
                .permitAll();
        return http.build();
    }*/
	 
	/*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/welcome")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }*/
	
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {
		
			http
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/sakums").permitAll()
			.requestMatchers("/login").permitAll()
			.requestMatchers("/register").permitAll()
			.requestMatchers("/logout").permitAll()
			.requestMatchers("/profils/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/game/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/upgrades/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/upgrades/update/**").hasAuthority("ADMIN")
			.requestMatchers("/h2-console/**").hasAuthority("ADMIN")
			);
			
			http.formLogin(form -> form
		            .loginPage("/login")
		            .usernameParameter("lietotajvards")
		            .passwordParameter("parole")
		            .successHandler(customAuthenticationSuccessHandler())
		            .permitAll()
		        );
			http.csrf(csrf-> csrf.disable());
			http.headers(frame-> frame.frameOptions(option->option.disable()));
			
		return http.build();
	}
}