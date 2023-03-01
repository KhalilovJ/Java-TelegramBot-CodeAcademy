//package az.code.frontapp.security;
//
//import az.code.frontapp.repo.OperatorRepoInterface;
//import az.code.frontapp.repo.OperatorUserRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
//@Slf4j
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public UserDetailsService userDetailsService(OperatorRepoInterface OperatorInterface, OperatorUserRepo ur) {
//        return username -> {
//            return ur.findByUsername(username).map(user -> User.builder()
//                    .username(user.getUsername())
//                    .password(user.getPassword())
//                    .roles("ADMIN")
//                    .build()).orElseThrow(() -> new UsernameNotFoundException("Not found"));
//        };
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//
//        http.csrf().disable();
//
////        http
////                .authorizeHttpRequests((requests) -> requests
////                        .requestMatchers("/vertretungsplan").hasAnyRole("SCHUELER", "LEHRER", "VERWALTUNG")
////                        .anyRequest().authenticated()
////                )
////                .formLogin((form) -> form
////                        .loginPage("/login")
////                        .permitAll()
////                )
////                .logout((logout) -> logout.permitAll());
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/home");
//    }
//}
