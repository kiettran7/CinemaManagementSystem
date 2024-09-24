package com.ttk.cinema.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] POST_PUBLIC_ENDPOINT = {"/users",
            "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh"
    };

    private final String[] GET_PUBLIC_ENPOINTS = {
            "/users/**", // xem thông tin bản thân
            "/tags", "/tags/**", // xem tag và list tag
            "/genres", "/genres/**", // xem loại và list
            "/items", "/items/**", // xem đồ đặt kèm và list
            "/promotions", "/promotions/**", // xem mã giảm giá và list
            "/show-rooms", "/show-rooms/**", // xem phòng và list
            "/show-schedules", "/show-schedules/**", // xem lịch chiếu và list
            "/seats", "/seats/**", // xem ghế và list
            "/bills", "/bills/**", // xem bill và list
            "/showtimes", "/showtimes/**", // xem giờ bắt đầu - kết thúc và list
            "/movies", "/movies/**" // xem phim và list
    };

    private final String[] CUSTOMER_GET_ENPOINT = {
            "/tickets/**", //xem vé đã đặt
            "/bills/**", // xem hóa đơn
    };

    private final String[] CUSTOMER_POST_ENPOINT = {
            "/tickets", //đặt vé trực tuyến
            "/bills" // xem hóa đơn
    };

    private final String[] CUSTOMER_PUT_ENPOINT = {
            "/tickets/**", //hủy vé đã đặt
            "/bills/**", // hủy hóa đơn
    };

    private final String[] STAFF_GET_ENPONIT = {
            "/tickets", "/tickets/**", //xem list vé đặt trực tiếp cho khách hàng
            "/bills", "/bills/**", // xem list hóa đơn
            "/items", "/items/**", // xem list sản phẩm
    };

    private final String[] STAFF_POST_ENPONIT = {
            "/tickets", //đặt vé trực tiếp cho khách hàng
            "/bills", // xem hóa đơn
            "/items", // thêm sản phẩm
    };

    private final String[] STAFF_DELETE_PUT_ENPONIT = {
            "/items/**",
            "/tickets/**",
            "/bills/**",
    };

    private final String[] ADMIN_GET_ENPONIT = {
            "/users",
            "/roles", "/roles/**",
            "/permissions", "/permissions/**",
            "/bills", "/bills/**",
            "/tickets", "/tickets/**",
    };

    private final String[] ADMIN_POST_ENPONIT = {
            "/tags",
            "/genres",
            "/items",
            "/promotions",
            "/show-rooms",
            "/show-schedules",
            "/seats",
            "/roles",
            "/tickets",
            "/bills",
            "/showtimes",
            "/movies"
    };

    private final String[] ADMIN_DELETE_PUT_ENPOINTS = {
            "/tags/**",
            "/genres/**",
            "/items/**",
            "/promotions/**",
            "/show-rooms/**",
            "/show-schedules/**",
            "/seats/**",
            "/roles/**",
            "/tickets/**",
            "/bills/**",
            "/showtimes/**",
            "/movies/**"
    };

    @Autowired
    private CustomJwtDecoder customJwtDecoder;

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, POST_PUBLIC_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_PUBLIC_ENPOINTS).permitAll()

                        .requestMatchers(HttpMethod.GET, CUSTOMER_GET_ENPOINT).hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, CUSTOMER_POST_ENPOINT).hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, CUSTOMER_PUT_ENPOINT).hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.GET, STAFF_GET_ENPONIT).hasRole("STAFF")
                        .requestMatchers(HttpMethod.POST, STAFF_POST_ENPONIT).hasRole("STAFF")
                        .requestMatchers(HttpMethod.PUT, STAFF_DELETE_PUT_ENPONIT).hasRole("STAFF")
                        .requestMatchers(HttpMethod.DELETE, STAFF_DELETE_PUT_ENPONIT).hasRole("STAFF")

                        .requestMatchers(HttpMethod.GET, ADMIN_GET_ENPONIT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, ADMIN_POST_ENPONIT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, ADMIN_DELETE_PUT_ENPOINTS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, ADMIN_DELETE_PUT_ENPOINTS).hasRole("ADMIN")

                        .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                                jwtConfigurer.decoder(customJwtDecoder)
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwxbe1pau",
                "api_key", "922775612435143",
                "api_secret", "BC68YWVKFnsO_v5SmrW1ZzDUu3c",
                "secure", true));
        return cloudinary;
    }

}
