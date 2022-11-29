package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Turno;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITurnoService {
    Turno guardarTurno (Turno turno);
    Optional<Turno> buscarPorId(Long id);
    Optional<Set<Turno>> buscarPorOdontologoId(Long odontologoId);
    Optional<Set<Turno>> buscarPorPaciente(Long pacienteId);
    List<Turno> mostrarTurnos();
    void actualizarTurno(Turno turno);
    void eliminarTurno(Long id);
}
