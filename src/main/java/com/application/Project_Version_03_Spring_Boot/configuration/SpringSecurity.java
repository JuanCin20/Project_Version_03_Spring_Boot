package com.application.Project_Version_03_Spring_Boot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import com.application.Project_Version_03_Spring_Boot.service.UserService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import java.util.List;
// import org.springframework.security.core.userdetails.UserDetails;
// import java.util.ArrayList;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.http.HttpHeaders;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurity implements WebMvcConfigurer {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // return httpSecurity.csrf(csrfConfigurer -> csrfConfigurer.disable()).httpBasic(Customizer.withDefaults()).sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
        return httpSecurity.cors(Customizer.withDefaults()).headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin())).csrf(csrfConfigurer -> csrfConfigurer.disable()).httpBasic(Customizer.withDefaults()).sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            // Configure the Public Endpoints
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/ProjectVersion03SpringBootApplication/Initializer").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/Css/**").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/Images/**").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/Js/**").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/ProjectVersion03SpringBootApplication/index").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/user/all").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/user/allRoleEntities").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/user/{UserId}").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/rest/user/save").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/api/rest/user/update").permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/api/rest/user/delete/{UserId}").permitAll();

            // Configure the Private Endpoints
            // authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/category/all").hasAnyAuthority("CREATE", "READ");
            // authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/category/all").hasAuthority("CREATE");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/h2-console/**").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/category/all").hasAnyRole("Developer", "Administrator");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/category/{CategoryId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/rest/category/save").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/api/rest/category/update").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/api/rest/category/delete/{CategoryId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/brand/all").hasAnyRole("Developer", "Administrator");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/brand/{BrandId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/rest/brand/save").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/api/rest/brand/update").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/api/rest/brand/delete/{BrandId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/product/all").hasAnyRole("Developer", "Administrator");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/rest/product/{ProductId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/rest/product/save/{CategoryId}/{BrandId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/api/rest/product/update/{ProductId}").hasRole("Developer");
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/api/rest/product/delete/{ProductId}").hasRole("Developer");

            // authorizationManagerRequestMatcherRegistry.anyRequest().denyAll();
            // Configure the rest of Endpoints - Didn't Specification
            authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
        }).build();
    }

    /* @Bean
    public SecurityFilterChain securityFilterChainAlternative(HttpSecurity httpSecurity) throws Exception {

        // httpSecurity.authorizeHttpRequests((authorizationManagerRequestMatcherRegistry) -> authorizationManagerRequestMatcherRegistry.requestMatchers("/h2/project_version_01/**").permitAll().anyRequest().authenticated());
        // httpSecurity.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));

        return httpSecurity.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin())).csrf(csrfConfigurer -> csrfConfigurer.disable()).httpBasic(Customizer.withDefaults()).sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers("/h2/project_version_01/**").permitAll().anyRequest().authenticated();
        }).build();
    } */

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }

    /* @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(User.withUsername("U22208295").password("JU@NCIn080604").roles("ADMIN").authorities("CREATE", "READ").build());
        userDetails.add(User.withUsername("U22208294").password("JU@NCIn080604").roles("USER").authorities("READ").build());
        return new InMemoryUserDetailsManager(userDetails);
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    /* @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name()).allowedHeaders(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);
            }
        };
    } */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
    }
}