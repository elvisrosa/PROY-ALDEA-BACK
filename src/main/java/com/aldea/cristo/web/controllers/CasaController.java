package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/casa")
public class CasaController {

    @Autowired
    @Qualifier("servicioCasa")
    private InterfazGenerica servicioCasa;

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
    public ResponseEntity<CasaEntity> crear(@RequestBody CasaEntity casa) {
        try {
            servicioCasa.save(casa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(casa);
    }

    @PutMapping("/actualizar/{idcasa}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CasaEntity> actualizarCasa(@RequestBody CasaEntity casa, @PathVariable Integer idcasa) {
        try {
            CasaEntity casaEncontrada = (CasaEntity) servicioCasa.findBId(idcasa);

            casaEncontrada.setDireccion(casa.getDireccion());
            casaEncontrada.setNumeroCasa(casa.getDireccion());
            casaEncontrada.setTelefono(casa.getTelefono());
            casaEncontrada.setIdCasa(casa.getIdCasa());
            casaEncontrada.setNiños(casa.getNiños());
            servicioCasa.save(casa);

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
