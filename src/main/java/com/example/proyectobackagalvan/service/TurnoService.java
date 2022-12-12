package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.exception.BadRequestException;
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
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    private final Logger LOGGER = Logger.getLogger(TurnoService.class);
    private final ObjectMapper mapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ObjectMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.mapper = mapper;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir ese turno en un turno DTO
        TurnoDTO respuesta=new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        //cargar los elementos
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        //asociar cada elemento
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        //salida
        return turno;
    }

    // Al hacerlo con mapper los POST de Postman retornan null en los id de paciente y odontólogo, no está mappeando bien los objetos
//    private TurnoDTO turnoATurnoDTO(Turno turno) {
//        return mapper.convertValue(turno, TurnoDTO.class);
//    }
//    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
//        return mapper.convertValue(turnoDTO, Turno.class);
//    }

    public TurnoDTO guardarTurno (TurnoDTO turnoDTO) throws ResourceNotFoundException, BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turnoDTO.getPacienteId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turnoDTO.getOdontologoId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            LOGGER.info("Se registró un nuevo turno con id="+turnoDTO.getId());
            return turnoATurnoDTO(turnoRepository.save(turnoDTOaTurno(turnoDTO)));
        } else {
            LOGGER.warn("Por favor revise que los datos del paciente y odontólogo sean correctos");
            throw new BadRequestException("Por favor revise que los datos del paciente y odontólogo sean correctos");
        }
    }
    public Optional<TurnoDTO> buscarTurno(Long id) throws ResourceNotFoundException, BadRequestException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turnoBuscado.get().getId());
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turnoBuscado.get().getId());
            if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
                LOGGER.info("Se encontró un turno con id="+id);
                return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
            } else {
                LOGGER.warn("Error al actualizar, verifique si el odontólogo y/o paciente asociado existe");
                throw new BadRequestException("Error al actualizar, verifique si el odontólogo y/o paciente asociado existe");
            }
        } else {
            LOGGER.warn("No se encontró ningún turno con id="+id);
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
    public void actualizarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException, BadRequestException {
        buscarTurno(turnoDTO.getId());
        turnoRepository.save(turnoDTOaTurno(turnoDTO));
        LOGGER.info("Iniciando la actualización del turno con id="+turnoDTO.getId());
    }
    public void eliminarTurno(Long id) throws ResourceNotFoundException, BadRequestException {
        buscarTurno(id);
        turnoRepository.deleteById(id);
        LOGGER.info("Se eliminó al turno con id="+id);
    }
}
