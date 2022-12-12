package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.exception.BadRequestException;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoDTO guardarTurno (TurnoDTO turno) throws ResourceNotFoundException, BadRequestException;
    Optional<TurnoDTO> buscarTurno(Long id) throws ResourceNotFoundException, BadRequestException;
    List<TurnoDTO> buscarPorOdontologo(Long odontologoId);
    List<TurnoDTO> buscarPorPaciente(Long pacienteId);
    List<TurnoDTO> mostrarTurnos();
    void actualizarTurno(TurnoDTO turno) throws ResourceNotFoundException, BadRequestException;
    void eliminarTurno(Long id) throws ResourceNotFoundException, BadRequestException;
}
