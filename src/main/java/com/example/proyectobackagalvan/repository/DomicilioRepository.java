package com.example.proyectobackagalvan.repository;

import com.example.proyectobackagalvan.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}