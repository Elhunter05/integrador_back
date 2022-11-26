package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) { this.turnoRepository = turnoRepository; }
    public Turno guardarTurno (Turno turno) { return turnoRepository.save(turno); }
    public Optional<Turno> buscarTurno(Long id) { return turnoRepository.findById(id); }
    public Optional<Set<Turno>> buscarTurnosPorOdontologo(Odontologo odontologo) { return turnoRepository.findByOdontologo(odontologo); }
    public Optional<Set<Turno>> buscarTurnosPorPaciente(Paciente paciente) { return turnoRepository.findByPaciente(paciente); }
    public List<Turno> listarTurnos() { return turnoRepository.findAll(); }
    public void actualizarTurno(Turno turno) { turnoRepository.save(turno); }
    public void eliminarTurno(Long id) { turnoRepository.deleteById(id); }

}
