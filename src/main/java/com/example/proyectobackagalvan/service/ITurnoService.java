package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoDTO guardarTurno (TurnoDTO turno);
    Optional<TurnoDTO> buscarTurno(Long id);
    List<TurnoDTO> buscarPorOdontologo(Long odontologoId);
    List<TurnoDTO> buscarPorPaciente(Long pacienteId);
    List<TurnoDTO> mostrarTurnos();
    void actualizarTurno(TurnoDTO turno);
    void eliminarTurno(Long id);
}
