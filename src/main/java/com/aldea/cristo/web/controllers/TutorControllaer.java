package com.aldea.cristo.web.controllers;
import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.entities.TutoraEntity;
import com.aldea.cristo.servicios.TutorService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/tutor")
public class TutorControllaer {
    
    @Autowired
    //@Qualifier("servicioTutor")
    private TutorService servicioTutor;
    
    @GetMapping("/listar")
    public List<TutoraEntity> listar(){
        return servicioTutor.findAll();
    }
    
    @GetMapping("/listar/{idTutor}")
    public TutoraEntity listar(@PathVariable Integer idTutor){
        return servicioTutor.findBId(idTutor);
    }
    
    @PutMapping("/{tutorId}/casa/{cursoId}")
    public TutoraEntity asignarTutorToCurso(@PathVariable Integer tutorId,@PathVariable Integer cursoId ){
        return servicioTutor.asignarTutorToCurso(tutorId, cursoId);
    }
    
    @DeleteMapping("/eliminar/{tutorId}")
    public void asignarTutorToCurso(@PathVariable Integer tutorId){
        servicioTutor.delete(tutorId);
    }
    
    @PostMapping("/crear")
    public TutoraEntity crear(@RequestBody TutoraEntity tutor){
        return servicioTutor.save(tutor);
    }
    
    @GetMapping("/listarcasas/{idTutor}")
    public Set<CasaEntity> listarCasasTutores(@PathVariable Integer idTutor){
        return servicioTutor.getCasasByTutorId(idTutor);
    }
    
  
    
    
}
