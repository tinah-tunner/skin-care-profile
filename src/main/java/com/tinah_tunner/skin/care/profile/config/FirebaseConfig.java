package com.tinah_tunner.skin.care.profile.config;

import java.io.ByteArrayInputStream;
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
                System.out.println("Firebase service account key not found; skipping Firebase initialization.");
                return;
            }

            byte[] serviceAccountBytes = serviceAccount.readAllBytes();
            if (serviceAccountBytes.length == 0) {
                System.out.println("Firebase service account key is empty; skipping Firebase initialization.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(serviceAccountBytes)))
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