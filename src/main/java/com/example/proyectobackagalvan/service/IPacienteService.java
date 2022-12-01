package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.PacienteDTO;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    PacienteDTO guardarPaciente (PacienteDTO paciente);
    Optional<PacienteDTO> buscarPaciente(Long id);
    Optional<PacienteDTO> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<PacienteDTO> buscarPorEmail(String email);
    List<PacienteDTO> mostrarPacientes();
    void actualizarPaciente(PacienteDTO paciente);
    void eliminarPaciente(Long id);
}
