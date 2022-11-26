package com.example.proyectobackagalvan.repository;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Optional<Set<Turno>> findByOdontologo(Odontologo odontologo);
    Optional<Set<Turno>> findByPaciente(Paciente paciente);
}
