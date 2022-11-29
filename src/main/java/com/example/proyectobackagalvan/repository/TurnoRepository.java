package com.example.proyectobackagalvan.repository;

import com.example.proyectobackagalvan.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Optional<Set<Turno>> findByOdontologoId(Long odontologoId);
    Optional<Set<Turno>> findByPacienteId(Long pacienteId);
}
