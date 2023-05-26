package com.apiFinal.eCommerce.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apiFinal.eCommerce.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) //habilita o cors
            .csrf(csrf -> csrf.disable()) //desabilita o csrf
            .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler)) //configura a classe para tratamento da excecao de autenticacao
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //define a politica de sessao
            //Essas linhas que definimos quais rotas serão publicas e quais privadas
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/auth/**", "/roles/**", "/test/all", "/swagger-ui/**").permitAll()
            		.requestMatchers(HttpMethod.GET, "/instrutores/**", "/turmas/**").hasAnyRole("USER", "INSTRUTOR", "DIRETORIA")
                    .requestMatchers(HttpMethod.GET, "/telefones/**").hasAnyRole("INSTRUTOR", "DIRETORIA") 
                    .requestMatchers(HttpMethod.POST, "/telefones/**").hasAnyRole("INSTRUTOR", "DIRETORIA") 
                    .requestMatchers(HttpMethod.PUT, "/telefones/**").hasAnyRole("INSTRUTOR", "DIRETORIA") 
                    .requestMatchers(HttpMethod.DELETE, "/telefones/**").hasAnyRole("INSTRUTOR", "DIRETORIA") 
                    .requestMatchers(HttpMethod.POST, "/instrutores/**", "/turmas/**").hasAnyRole("DIRETORIA")
                    .requestMatchers(HttpMethod.PUT, "/instrutores/**", "/turmas/**").hasAnyRole("DIRETORIA")
                    .requestMatchers(HttpMethod.DELETE, "/instrutores/**", "/turmas/**").hasAnyRole("DIRETORIA")
                    .anyRequest().authenticated()) //demais rotas, nao configuradas acima, so poderao ser acessadas mediante autenticacao
		;		
		
		http.authenticationProvider(authenticationProvider()); //define o provedor de autenticacao

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); //define o filtro a ser aplicado no ciclo de vida da requisicao
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}