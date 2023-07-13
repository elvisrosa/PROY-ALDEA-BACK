package com.aldea.cristo.web.controllers;

import com.aldea.cristo.persistencia.entities.BautismoEntity;
import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.entities.NinoEntity;
import java.util.List;
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
import com.aldea.cristo.persistencia.interfaces.InterfazGenerica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class NinoControlller {

    public static final Logger logger = LoggerFactory.getLogger(NinoControlller.class);

    @Autowired
    @Qualifier("servicioNino")
    private InterfazGenerica servicioNino;
    //private NinoInterface servicioNino;

    @Autowired
    @Qualifier("servicioBautizo")
    private InterfazGenerica servicioBautizo;

    @Autowired
    @Qualifier("servicioCasa")
    private InterfazGenerica servicioCasa;

    //private Bautizonterface servicioBautizo;
    @GetMapping("/niños")
    public List<NinoEntity> listarNiños() {
        return servicioNino.findAll();
    }

    @GetMapping("/niños/{cedula}")
    public NinoEntity listarCedula(@PathVariable String cedula) {
        return (NinoEntity) servicioNino.findBId(cedula);
    }

    @PostMapping("/niños/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NinoEntity> crear(@RequestBody NinoEntity datos) {

        try {
            //NIÑO
            NinoEntity nino = new NinoEntity();
            nino.setAusente(false);
            nino.setCedula(datos.getCedula());
            nino.setNombres(datos.getNombres());
            nino.setApellidos(datos.getApellidos());
            nino.setLugarNacimiento(datos.getLugarNacimiento());
            nino.setEdad(datos.getEdad());
            nino.setSexo(datos.getSexo());

            //BAUTISMO
            if (datos.getBautizo() != null) {
                BautismoEntity bautismo = new BautismoEntity();
                bautismo.setFecha(datos.getBautizo().getFecha());
                bautismo.setDescripcionPadrino(datos.getBautizo().getDescripcionPadrino());
                bautismo.setMatrimoniosPadres(datos.getBautizo().getMatrimoniosPadres());
                nino.setBautizo(bautismo);
                servicioBautizo.save(bautismo);
            }

            //CASA
            if (datos.getCasa().getIdCasa() != null) {
                CasaEntity casa = (CasaEntity) servicioCasa.findBId(datos.getCasa().getIdCasa());
                nino.setCasa(casa);
            }

            //PADRE
            //MADRE
            //REGISTRANDO TODOS LOS DATOS
            servicioNino.save(nino);

            return ResponseEntity.ok(nino);
        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/eliminar/{cedula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String cedula) {
        servicioNino.delete(cedula);
    }

    @PutMapping("/niños/{cedula}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NinoEntity> update(@RequestBody NinoEntity datos, @PathVariable String cedula) {
        try {
            NinoEntity niñoe = (NinoEntity) servicioNino.findBId(cedula);
            niñoe.setAusente(datos.getAusente());
            niñoe.setCedula(datos.getCedula());
            niñoe.setApellidos(datos.getApellidos());
            niñoe.setEdad(datos.getEdad());
            niñoe.setFechaNacimiento(datos.getFechaNacimiento());
            niñoe.setLugarNacimiento(datos.getLugarNacimiento());
            niñoe.setSexo(datos.getSexo());
            niñoe.setNombres(datos.getNombres());
            BautismoEntity bautismo = niñoe.getBautizo();
            niñoe.setBautizo(bautismo);
            servicioNino.save(niñoe);
            return ResponseEntity.ok(niñoe);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //END POINT AGREGAR PDF
    /*
    Puedes utilizar la anotación @PostMapping para definir el endpoint y la anotación
    @RequestParam("file") para recibir el archivo PDF en el cuerpo de la solicitud.
     */
 /*
    @RestController
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Validar y procesar el archivo
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha enviado ningún archivo");
        }

        try {
            // Guardar el archivo
            String fileName = fileStorageService.saveFile(file);

            // Retornar una respuesta exitosa con el nombre del archivo guardado
            return ResponseEntity.ok().body("Archivo guardado: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo");
        }
    }
}

    
     */
}
