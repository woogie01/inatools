package inatools.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String accessTokenKey = "Access Token";
        String refreshTokenKey = "Refresh Token";

        SecurityScheme accessTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityScheme refreshTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Refresh-Token");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(accessTokenKey)
                .addList(refreshTokenKey);

        Components components = new Components()
                .addSecuritySchemes(accessTokenKey, accessTokenSecurityScheme)
                .addSecuritySchemes(refreshTokenKey, refreshTokenSecurityScheme);

        return new OpenAPI()
                .info(apiInfo())
                .security(Collections.singletonList(securityRequirement))
                .components(components);
    }

    private Info apiInfo() {
        return new Info()
                .title("No졸중 API") // API의 제목
                .description("Let's practice Swagger UI") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
