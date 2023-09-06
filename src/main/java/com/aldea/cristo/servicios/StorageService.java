package com.aldea.cristo.servicios;

import com.aldea.cristo.persistencia.interfaces.StorageInterface;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class StorageService implements StorageInterface {

    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() {
        rootLocation = Paths.get(mediaLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException ex) {
            Logger.getLogger(StorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String store(MultipartFile file) {
        System.out.println("Antes del error " + file.getContentType());
        if (file.isEmpty()) {
            throw new RuntimeException("Fallo la carga del archivo");
        }
        //Path path = Paths.get("/bitacora.docx");
        //String nombre_bitacora_randon = UUID.randomUUID().toString();
        String filename = file.getOriginalFilename();
        Path destintionFile = rootLocation.resolve(Paths.get(filename))
                .normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destintionFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(StorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filename;
    }

    @Override
    public Resource loadResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo leer el archivo o no existe" + filename);
            }
        } catch (RuntimeException | MalformedURLException e) {
            throw new RuntimeException("No se pudo leer el archivo" + filename + e.getMessage());
        }
    }

    @Override
    public String save_bitacora(Integer id_bitacora) {
        Path sourcePath = Paths.get("bitacorafiles/bitacora.docx");
        if(!Files.exists(sourcePath)){
            try {
                Files.createFile(sourcePath);
            } catch (IOException ex) {
                Logger.getLogger(StorageService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Exsite " + Files.exists(sourcePath));
        //Path destionationPath = rootLocation.resolve("bitacorafiles");
        //String filename = UUID.randomUUID().toString() + ".docx";
        String filename = "bitacora-"+id_bitacora+".docx";
        try {
            Files.copy(sourcePath, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("ERROR" + e.getMessage());
        }
        return filename;
    }

}
