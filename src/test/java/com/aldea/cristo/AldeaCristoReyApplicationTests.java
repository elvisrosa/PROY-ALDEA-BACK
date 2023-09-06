package com.aldea.cristo;

import com.aldea.cristo.servicios.UserRoleService;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AldeaCristoReyApplicationTests {

    @Autowired
    private UserRoleService service;
    
    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("myPassword");
        assertTrue(encoder.matches("myPassword", result));
    }
    
    @Test
    void eliminarRol(){
        String username = "gigigig888";
        boolean elimiado = service.eliminar(username);
        if(elimiado){
            System.out.println("Eliminado con exito");
        }else{
            System.out.println("Error al eliminadr");
        }
    }

}
