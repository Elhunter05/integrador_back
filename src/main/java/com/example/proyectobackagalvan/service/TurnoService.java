package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import com.example.proyectobackagalvan.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements ITurnoService {
    private final TurnoRepository turnoRepository;
    private final Logger LOGGER = Logger.getLogger(TurnoService.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno) {
        return mapper.convertValue(turno, TurnoDTO.class);
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        return mapper.convertValue(turnoDTO, Turno.class);
    }

    public TurnoDTO guardarTurno (TurnoDTO turnoDTO) {
        LOGGER.info("Se registró un nuevo turno con id="+turnoDTO.getId());
        return turnoATurnoDTO(turnoRepository.save(turnoDTOaTurno(turnoDTO)));
    }
    public Optional<TurnoDTO> buscarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            LOGGER.info("Se encontró un turno con id="+id);
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        } else {
            throw new ResourceNotFoundException("No se encontró ningún turno con id="+id);
        }
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
    public void actualizarTurno(TurnoDTO turnoDTO) {
        LOGGER.info("Iniciando la actualización del turno con id="+turnoDTO.getId());
        turnoRepository.save(turnoDTOaTurno(turnoDTO));
    }
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurno(id).isEmpty()) {
            throw new ResourceNotFoundException("No existe ningún turno con id="+id);
        }
        turnoRepository.deleteById(id);
        LOGGER.info("Se eliminó al turno con id="+id);
    }
}
