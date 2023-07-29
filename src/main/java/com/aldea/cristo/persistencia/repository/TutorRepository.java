package com.aldea.cristo.persistencia.repository;

import com.aldea.cristo.persistencia.entities.CasaEntity;
import com.aldea.cristo.persistencia.entities.TutoraEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TutorRepository extends CrudRepository<TutoraEntity, Integer> {

    @Query("SELECT casa FROM TutoraEntity tutora JOIN tutora.casas casa WHERE tutora.idTutora = :tutoraId")
    Set<CasaEntity> findCasasByTutoraId(@Param("tutoraId") Integer tutoraId);

}
