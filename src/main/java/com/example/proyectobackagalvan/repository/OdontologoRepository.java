package com.example.proyectobackagalvan.repository;

import com.example.proyectobackagalvan.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

//    @Query("SELECT o FROM Odontologos o WHERE o.matricula=?1")
    Optional<Odontologo> findByMatricula(Integer matricula);

}
