package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.BitacoraEntity;
import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.servicios.BitacoraService;
import com.aldea.cristo.servicios.CasaService;
import com.aldea.cristo.servicios.TutorService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/casa")
public class CasaController {

    public static final Logger logger = LoggerFactory.getLogger(CasaController.class);

    @Autowired
    //@Qualifier("servicioCasa")
    private CasaService servicioCasa;

    @Autowired
    //@Qualifier("servicioTutor")
    private TutorService servicioTutor;

    @Autowired
    private MediaControlador controlador;
    
    @Autowired
    private BitacoraService bitacoraService;

    /*@Autowired
    public CasaController(MediaControlador mediaControlador) {
        this.controlador = mediaControlador;
    }*/
    //@Autowired
    //private UploadServices uploadService;
    @GetMapping("/listar")
    public List<CasaEntity> listarTodo() {
        return servicioCasa.findAll();
    }

    @GetMapping("/ver/{idcasa}")
    public CasaEntity listarTodo(@PathVariable Integer idcasa) {
        return (CasaEntity) servicioCasa.findBId(idcasa);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@RequestBody CasaEntity casa) throws IOException {
        BitacoraEntity bitacora = new BitacoraEntity();
        //bitacora.setArchivo(byteArchivo);
        //casa.setBitacora(bitacora);
        casa.setEstado(1);
        CasaEntity casa_creada = servicioCasa.save(casa);
        System.out.println("Casa creada " + casa_creada);
        bitacora.setDescripcion("Esta bitacora es perteneciente de la casa: ".concat(casa_creada.getNumerocasa()));
        bitacora.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        String urlBitacora = controlador.save_bitacora(casa_creada.getIdCasa());
        bitacora.setUrl(urlBitacora);
        bitacora.setCasa(casa_creada);
        
        bitacoraService.save(bitacora);
        //casa_creada.setBitacora(bitacora);
        // controlador.uploadFile();
        //bitacora.setUrl(urlBitacora);
        return ResponseEntity.ok(casa);
    }

    @PutMapping("/actualizar/{idcasa}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CasaEntity> actualizarCasa(@RequestBody CasaEntity casa, @PathVariable Integer idcasa) {
        try {
            CasaEntity casaEncontrada = (CasaEntity) servicioCasa.findBId(idcasa);
            casaEncontrada.setEstado(casa.getEstado());
            casaEncontrada.setDireccion(casa.getDireccion());
            casaEncontrada.setNumerocasa(casa.getNumerocasa());
            casaEncontrada.setNombrecasa(casa.getNombrecasa());
            servicioCasa.save(casaEncontrada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(casa);
    }

    @PatchMapping("/cambiarestado/{idcasa}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cambiarEstadoCasa(@PathVariable Integer idcasa) {
        CasaEntity casaEncontrada = null;
        try {
            casaEncontrada = (CasaEntity) servicioCasa.findBId(idcasa);
            if (casaEncontrada.getEstado() == 1) {
                casaEncontrada.setEstado(0);
            } else if (casaEncontrada.getEstado() == 0) {
                casaEncontrada.setEstado(1);
            }
            servicioCasa.save(casaEncontrada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok(casaEncontrada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            servicioCasa.delete(id);
            return ResponseEntity.ok().body("{\"message\": \"Registro eliminado con éxito\", \"status\": \"succes\"}");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Registro no puede ser eliminado porque aun tiene registros asignados (NIÑOS-TUTORES), vacia la casa y vuelve a intentarlo";
            return ResponseEntity.ok().body("{\"message\": \"" + errorMessage + "\", \"status\": \"error\"}");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Registro no existe\", \"status\": \"error\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error al eliminar registro\", \"status\": \"error\"}");
        }

    }

    private String extractForeignKeyErrorMessage(DataIntegrityViolationException e) {
        String[] lines = e.getMessage().split("\n");
        String lastLine = lines[lines.length - 1];
        if (lastLine.contains("ERROR:")) {
            return lastLine.substring(lastLine.indexOf("ERROR:") + 7);
        } else {
            return "Error de integridad en la base de datos.";
        }
    }

}
