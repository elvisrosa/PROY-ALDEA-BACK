package com.aldea.cristo.servicios;

import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

//@Service
public class UploadServices {

    public String subirArchivo(MultipartFile file) {
        //Otenemos el buket -- es donde se subira el archivi
        Bucket bucket = StorageClient.getInstance().bucket("aldea-cristo-rey.appspot.com");
        try {
            //subir archivo al buscket
            Blob blob = bucket.create(file.getOriginalFilename(), file.getBytes());
            //obtener url del archivo subido
            
            String donwloadUrl = blob.getMediaLink();
            
            return "Archivo subido correctamente: Descargalo con la URL".concat(donwloadUrl);
        
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(UploadServices.class.getName()).log(Level.SEVERE, null, ex);
            return "error al subir archivo a la nube";
        }
    }

}
