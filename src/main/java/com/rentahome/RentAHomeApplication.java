package com.rentahome;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.rentahome.firebase.FCMInitializer;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.io.FileInputStream;
import java.io.IOException;


@SpringBootApplication
public class RentAHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentAHomeApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void initializeFirebase() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("rentahome-62e7c-firebase-adminsdk-938cp-d6d0c59267.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        System.out.println("Initialized firebase app");
    }

    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

}
