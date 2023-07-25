package com.aldea.cristo.firebaseConfig;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class firebaseConfig {

    //@Value("${firebase.configPath}")
    private String firebaseConfigPath;

    //@Bean
    public Firestore firestore() throws FileNotFoundException, IOException {
        FileInputStream serviceAccount
                = new FileInputStream(firebaseConfigPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore(firebaseApp);

    }

}
