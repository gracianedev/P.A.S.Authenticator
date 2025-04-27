
package com.pas.pasauthenticator;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PasauthenticatorApplication {

    public static void main(String[] args) {
                Dotenv dotenv = Dotenv.load();

        // Mail
        System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));

        // Datasource
        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("spring.datasource.url"));
        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("spring.datasource.username"));
        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("spring.datasource.password"));

              
        
        SpringApplication.run(PasauthenticatorApplication.class, args);
    }
}
