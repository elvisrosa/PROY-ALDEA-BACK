package com.aldea.cristo.web.controllers;

import com.aldea.cristo.servicios.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/media")
@AllArgsConstructor
public class MediaControlador {

    private final StorageService storageService;
    private final HttpServletRequest request;

    //@PostMapping("/upload")
    //public Map<String, String> uploadFile(){ //@RequestParam("file") MultipartFile multiparFile
    public String uploadFile(MultipartFile multiparFile) { //@RequestParam("file") MultipartFile multiparFile
        String path = storageService.store(multiparFile);
        //String path = "/bitacora.docx";       
        // String path = "/default.docx";       
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();

        //return Map.of("url",url);
        return url;
    }

    public String save_bitacora(Integer id_casa) { //@RequestParam("file") MultipartFile multiparFile
        String path = storageService.save_bitacora(id_casa);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();

        //return Map.of("url",url);
        return url;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> cargar() {
        String ruta_archivo = storageService.save_bitacora(152);
        return ResponseEntity.ok(ruta_archivo);
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) throws IOException {
        try {
            Resource file = storageService.loadResource(filename);
            String contentType = Files.probeContentType(file.getFile().toPath());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La bitácora no existe.");

        }

    }

}
