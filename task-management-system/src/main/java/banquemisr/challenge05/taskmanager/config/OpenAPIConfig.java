package banquemisr.challenge05.taskmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "Task Manager API",
                version = "1.0",
                description = "API documentation for the Task Manager application",
                license = @License(name = "Apache 2.0", url = "http://springdoc.org")
        ),
        servers = {
                @Server(url = "/", description = "Default Server URL"),
                @Server(url = "/myapp", description = "Server for MyApp")
        },
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenAPIConfig {

}
