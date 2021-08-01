package ch.helvetia.helix.infra.api.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ch.helvetia.helix")).build();
    }

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Philippe Wanner", "https://www.linkedin.com/in/philippe-wanner/", "");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Helvetia Helix - Architecture Interview", // title
            null, // description
            null, //version
            null, // termsOfServiceUrl
            DEFAULT_CONTACT, // contact
            "GNU General Public License v3.0", // license
            "https://www.gnu.org/licenses/gpl-3.0.en.html", // licenseUrl
            Collections.emptyList()); // vendorExtensions

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json", "application/xml")
    );
}
