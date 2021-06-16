package com.rentahome.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FCMInitializer {
    public void initialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("rentahome-62e7c-firebase-adminsdk-938cp-d6d0c59267.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
}
