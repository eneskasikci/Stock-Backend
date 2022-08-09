package tr.edu.anadolu.productlistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProductListAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductListAppApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                /*registry.addMapping("/**").allowedOrigins("http://10.27.34.164:3000",
                        "http://10.29.3.157:*",
                        "http://10.27.32.84:*",
                        "http://10.29.3.158:*");
            }*/
                // sonradan düzenlenecek
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "PUT", "POST", "DELETE");
            }
        };
    }
}
