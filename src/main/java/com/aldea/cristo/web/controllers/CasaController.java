package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.BitacoraEntity;
import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.entities.TutoraEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import com.aldea.cristo.servicios.CasaService;
import com.aldea.cristo.servicios.TutorService;
import com.aldea.cristo.servicios.UploadServices;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> crear(@RequestBody CasaEntity casa) {
        try {
            BitacoraEntity bitacora = new BitacoraEntity();
            //bitacora.setArchivo(byteArchivo);
            bitacora.setDescripcion("Esta bitacora es perteneciente de la casa: ".concat(casa.getNumeroCasa()));
            bitacora.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            casa.setBitacora(bitacora);
            casa.setEstado(1);
            bitacora.setCasa(casa);
            servicioCasa.save(casa);
            
            return ResponseEntity.ok(casa);
            
        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //File newFile = new File("uploads", file.getOriginalFilename());
    //newFile.createNewFile();
    //try (FileWriter writer = new FileWriter(newFile)) {
    //writer.write(byteArchivo);
    //System.out.println(Arrays.toString(byteArchivo));
    //}
    @PutMapping("/actualizar/{idcasa}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CasaEntity> actualizarCasa(@RequestBody CasaEntity casa, @PathVariable Integer idcasa) {
        try {
            CasaEntity casaEncontrada = (CasaEntity) servicioCasa.findBId(idcasa);
            casaEncontrada.setEstado(casa.getEstado());
            casaEncontrada.setDireccion(casa.getDireccion());
            casaEncontrada.setNumeroCasa(casa.getNumeroCasa());
            casaEncontrada.setTelefono(casa.getTelefono());

            /*BitacoraEntity bitc = new BitacoraEntity();
            bitc.setDescripcion(casa.getBitacora().getDescripcion());
            bitc.setCasa(casa);
            casaEncontrada.setBitacora(bitc);*/
            servicioCasa.save(casaEncontrada);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(casa);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        try {
            servicioCasa.delete(id);
            mensaje.put("respuesta", "Casa eliminada");
            
        } catch (Exception e) {
            mensaje.put("respuesta", "Error al eliminar, verifica que la casa a eliminar no tenga alumnos aún en ella");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(mensaje);
        
    }
}
