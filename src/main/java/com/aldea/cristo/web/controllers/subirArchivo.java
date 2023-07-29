package com.aldea.cristo.web.controllers;
import com.aldea.cristo.servicios.UploadServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/cargar")
public class subirArchivo {
    
    
    //@Autowired
    private UploadServices cargarService;
       
    @PostMapping("/archivo")
    @ResponseStatus(HttpStatus.CREATED)
    public String cargar(@PathVariable("file") MultipartFile file){
        return cargarService.subirArchivo(file);
    }
}
