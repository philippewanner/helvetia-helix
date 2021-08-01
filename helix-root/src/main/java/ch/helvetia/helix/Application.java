package ch.helvetia.helix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        Environment env = SpringApplication.run(Application.class, args).getEnvironment();
        String urlSwagger = String.format("http://localhost:%s/swagger-ui/index.html", env.getProperty("server.port"));
        log.info("Starting OpenAPI... Url: {}", urlSwagger);
    }
}
