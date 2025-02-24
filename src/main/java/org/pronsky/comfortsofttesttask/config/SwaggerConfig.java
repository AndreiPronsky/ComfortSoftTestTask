package org.pronsky.comfortsofttesttask.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Andrei Pronsky",
                        email = "andrey.pronsky@gmail.com",
                        url = "https://github.com/AndreiPronsky"
                ), description = "Open Api documentation",
                title = "Open Api specification",
                version = "1.0",
                license = @License(
                        name = "Backend-developer",
                        url = "https://www.linkedin.com/in/andrei-pronsky-404873243/"
                ),
                termsOfService = "localhost:8080/api/v1/files"
        ),
        servers = {
                @Server(
                        description = "LOCAL",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "DEV",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {

}
