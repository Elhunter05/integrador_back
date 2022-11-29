package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente (Paciente paciente);
    Optional<Paciente> buscarPorId(Long id);
    Optional<Paciente> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Paciente> buscarPorEmail(String email);
    List<Paciente> mostrarPacientes();
    void actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
}
