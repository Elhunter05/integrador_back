package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IPacienteService {
    private final PacienteRepository pacienteRepository;
    private final Logger LOGGER = Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente (Paciente paciente) {
        LOGGER.info("Se ha registrado exitosamente un nuevo paciente");
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id) {
        LOGGER.info("Iniciando la búsqueda de un paciente con id="+id);
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorNombreYApellido(String nombre, String apellido) {
        LOGGER.info("Iniciando la búsqueda de un paciente con nombre="+nombre+" y apellido="+apellido);
        return pacienteRepository.findByNombreAndApellido(nombre, apellido);
    }
    public Optional<Paciente> buscarPorEmail(String email) {
        LOGGER.info("Iniciando la búsqueda de un paciente con email="+email);
        return pacienteRepository.findByEmail(email);
    }
    public List<Paciente> mostrarPacientes() {
        LOGGER.info("Iniciando la búsqueda de todos los pacientes");
        return pacienteRepository.findAll();
    }
    public void actualizarPaciente(Paciente paciente) {
        LOGGER.info("Iniciando la actualización del paciente con id="+paciente.getId());
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id) {
        LOGGER.info("Iniciando la eliminación del paciente con id="+id);
        pacienteRepository.deleteById(id);
    }
}
