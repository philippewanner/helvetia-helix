package ch.helvetia.helix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        Environment env = SpringApplication.run(Application.class, args).getEnvironment();
        String urlSwagger = String.format("http://%s:%s/swagger-ui/index.html", //
                "localhost", //
                env.getProperty("server.port"));
        log.info("Starting OpenAPI... Url: {}", urlSwagger);
    }

    private static String getIpAddress(){
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
