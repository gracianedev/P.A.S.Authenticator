
package com.pas.pasauthenticator;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PasauthenticatorApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Mail
        if (dotenv.get("MAIL_USERNAME") != null) {
            System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
            System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
        }

        // Datasource
        if (dotenv.get("SPRING_DATASOURCE_URL") != null) {
            System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
            System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
            System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
        }
              
        
        SpringApplication.run(PasauthenticatorApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PasauthenticatorApplication.class);
    }
}
