package com.example.proyectobackagalvan.repository;

import com.example.proyectobackagalvan.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
