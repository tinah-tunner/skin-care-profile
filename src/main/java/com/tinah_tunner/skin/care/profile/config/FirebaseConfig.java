package com.tinah_tunner.skin.care.profile.config;

import java.io.InputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {

            InputStream serviceAccount =
                    getClass().getClassLoader().getResourceAsStream("firebase/serviceAccountKey.json");

            if (serviceAccount == null) {
                throw new RuntimeException("Firebase key not found in resources");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("🔥 Firebase initialized!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}