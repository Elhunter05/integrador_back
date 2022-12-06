package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements ITurnoService {
    private final TurnoRepository turnoRepository;
    private final Logger LOGGER = Logger.getLogger(TurnoService.class);

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno) {
        TurnoDTO respuesta = new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setFecha(turno.getFecha());
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();

        Paciente paciente = new Paciente();
        paciente.setId(turnoDTO.getPacienteId());
        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoDTO.getOdontologoId());

        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }

    public TurnoDTO guardarTurno (TurnoDTO turno) {
        Turno turnoAGuardar = turnoDTOaTurno(turno);
        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        LOGGER.info("Se ha registrado exitosamente un nuevo turno");
        return turnoATurnoDTO(turnoGuardado);
    }
    public Optional<TurnoDTO> buscarTurno(Long id) {
        LOGGER.info("Iniciando la búsqueda de un turno con id="+id);
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        return turnoBuscado.map(this::turnoATurnoDTO);
    }
    public List<TurnoDTO> buscarPorOdontologo(Long odontologoId) {
        LOGGER.info("Iniciando la búsqueda de un odontólogo con id="+odontologoId);
        List<Turno> turnosDeOdontologoBuscado = turnoRepository.findAllOdontologosById(odontologoId);
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno t: turnosDeOdontologoBuscado) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }
    public List<TurnoDTO> buscarPorPaciente(Long pacienteId) {
        LOGGER.info("Iniciando la búsqueda de un paciente con id="+pacienteId);
        List<Turno> turnosDePacienteBuscado = turnoRepository.findAllPacientesById(pacienteId);
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno t: turnosDePacienteBuscado) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }
    public List<TurnoDTO> mostrarTurnos() {
        LOGGER.info("Iniciando la búsqueda de todos los turnos");
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno t: turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }
    public void actualizarTurno(TurnoDTO turno) {
        LOGGER.info("Iniciando la actualización del turno con id="+turno.getId());
        Turno turnoAActualizar = turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
    }
    public void eliminarTurno(Long id) {
        LOGGER.info("Iniciando la eliminación del turno con id="+id);
        turnoRepository.deleteById(id);
    }
}
