package com.example.demo.repository;

import com.example.demo.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocalRepository extends JpaRepository<Local, Long> {
    @Query("SELECT l FROM Local l LEFT JOIN FETCH l.equipements WHERE l.idLocal = :id")
Local findByIdWithEquipements(@Param("id") Long id);

}
