package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente (Paciente paciente);
    Optional<Paciente> buscarPaciente(Long id) throws ResourceNotFoundException;
    Optional<Paciente> buscarPorEmail(String email) throws ResourceNotFoundException;
    List<Paciente> buscarPacientesPorNombreYApellido(String nombre, String apellido);
    List<Paciente> mostrarPacientes();
    void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
