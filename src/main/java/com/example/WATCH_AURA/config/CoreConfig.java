package com.example.WATCH_AURA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CoreConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Cho phép tất cả các đường dẫn
                .allowedOrigins("http://localhost:5173", "http://localhost:3000")  // Cung cấp các URL frontend mà bạn muốn cho phép
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")  // Các HTTP methods được phép
                .allowedHeaders("*")  // Chấp nhận tất cả các header từ client
                .exposedHeaders("Authorization")  // Expose headers cần thiết (ví dụ như Authorization)
                .allowCredentials(true)  // Cho phép cookie hoặc các thông tin xác thực khác
                .maxAge(3600);  // Thời gian tối đa cho phép cache preflight request (3600 giây = 1 giờ)
    }
}
