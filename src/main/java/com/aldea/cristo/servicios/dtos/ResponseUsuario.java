package com.aldea.cristo.servicios.dtos;
import com.aldea.cristo.persistencia.entities.TutoraEntity;
import java.util.List;
import lombok.Data;

/**
 *
 * @author elvis
 */
@Data
public class ResponseUsuario {
    
    private String username;
    private List<String> roles;
    private TutoraEntity tutor;
 
    
}
