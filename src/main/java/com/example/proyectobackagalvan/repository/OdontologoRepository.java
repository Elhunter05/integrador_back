package com.example.proyectobackagalvan.repository;

import com.example.proyectobackagalvan.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findByMatricula(Integer matricula);
    List<Odontologo> findAllByNombreAndApellido(String nombre, String apellido);
}
