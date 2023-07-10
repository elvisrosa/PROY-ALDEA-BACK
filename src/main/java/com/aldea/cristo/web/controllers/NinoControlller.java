package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.NinoEntity;
import com.aldea.cristo.persistencia.interfaces.NinoInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api")
public class NinoControlller {

    @Autowired
    private NinoInterface servicioNino;

    @GetMapping("/niños")
    public List<NinoEntity> listarNiños() {
        return servicioNino.findAll();
    }

    @GetMapping("/niños/{cedula}")
    public NinoEntity listarCedula(@PathVariable String cedula) {
        return servicioNino.findBId(cedula);
    }

    @PostMapping("/niños/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public NinoEntity crear(@RequestBody NinoEntity datos) {
        return servicioNino.save(datos);
    }

    @DeleteMapping("/eliminar/{cedula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String cedula) {
        servicioNino.delete(cedula);
    }

    @PutMapping("/niños/{cedula}")
    @ResponseStatus(HttpStatus.CREATED)
    public NinoEntity update(@RequestBody NinoEntity datos, @PathVariable String cedula) {
        NinoEntity niñoe = servicioNino.findBId(cedula);
        niñoe.setCedula(datos.getCedula());
        niñoe.setApellidos(datos.getApellidos());
        niñoe.setEdad(datos.getEdad());
        niñoe.setFechaNacimiento(datos.getFechaNacimiento());
        niñoe.setLugarNacimiento(datos.getLugarNacimiento());
        niñoe.setSexo(datos.getSexo());
        niñoe.setNombres(datos.getNombres());
        return servicioNino.save(niñoe);
    }
}
