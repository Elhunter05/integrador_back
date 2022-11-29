package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {
    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) { this.turnoRepository = turnoRepository; }
    public Turno guardarTurno (Turno turno) { return turnoRepository.save(turno); }
    public Optional<Turno> buscarPorId(Long id) { return turnoRepository.findById(id); }
    public Optional<Set<Turno>> buscarPorOdontologoId(Long odontologoId) { return turnoRepository.findByOdontologoId(odontologoId); }
    public Optional<Set<Turno>> buscarPorPaciente(Long pacienteId) { return turnoRepository.findByPacienteId(pacienteId); }
    public List<Turno> mostrarTurnos() { return turnoRepository.findAll(); }
    public void actualizarTurno(Turno turno) { turnoRepository.save(turno); }
    public void eliminarTurno(Long id) { turnoRepository.deleteById(id); }

}
