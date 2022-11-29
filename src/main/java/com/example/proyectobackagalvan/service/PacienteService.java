package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) { this.pacienteRepository = pacienteRepository; }
    public Paciente guardarPaciente (Paciente paciente) { return pacienteRepository.save(paciente); }
    public Optional<Paciente> buscarPorId(Long id) { return pacienteRepository.findById(id); }
    public Optional<Paciente> buscarPorNombreYApellido(String nombre, String apellido) { return pacienteRepository.findByNombreAndApellido(nombre, apellido); }
    public Optional<Paciente> buscarPorEmail(String email) { return pacienteRepository.findByEmail(email); }
    public List<Paciente> mostrarPacientes() { return pacienteRepository.findAll(); }
    public void actualizarPaciente(Paciente paciente) { pacienteRepository.save(paciente); }
    public void eliminarPaciente(Long id) { pacienteRepository.deleteById(id); }

}
