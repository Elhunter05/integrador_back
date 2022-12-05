package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.PacienteDTO;
import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.repository.PacienteRepository;
import com.example.proyectobackagalvan.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IPacienteService {
    private final PacienteRepository pacienteRepository;
    private final TurnoRepository turnoRepository;
    private final ObjectMapper mapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, TurnoRepository turnoRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
        this.mapper = mapper;
    }



    // Loguear cada método en los service



    private PacienteDTO pacienteAPacienteDTO(Paciente paciente) {
        PacienteDTO respuesta = new PacienteDTO();
        respuesta.setId(paciente.getId());
        respuesta.setNombre(paciente.getNombre());
        respuesta.setApellido(paciente.getApellido());
        respuesta.setDni(paciente.getDni());
        respuesta.setEmail(paciente.getEmail());
        respuesta.setFechaIngreso(paciente.getFechaIngreso());
        respuesta.setDomicilioId(paciente.getDomicilio().getId());

        Set<Turno> turnoSet = paciente.getTurnoSet();
        List<Long> turnoIdList = new ArrayList<>();
        for (Turno turno: turnoSet) {
            turnoIdList.add(turno.getId());
        }
        respuesta.setTurnoIdList(turnoIdList);

        return respuesta;
    }

    private Paciente pacienteDTOaPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();

        Domicilio domicilio = new Domicilio();
        domicilio.setId(pacienteDTO.getDomicilioId());

        paciente.setId(pacienteDTO.getId());
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setDni(pacienteDTO.getDni());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setFechaIngreso(pacienteDTO.getFechaIngreso());
        paciente.setDomicilio(domicilio);

        List<Long> turnoIdList = pacienteDTO.getTurnoIdList();
        Set<Turno> turnoSet = new HashSet<>();
        for (Long id: turnoIdList) {
            Optional<Turno> turnoEncontrado = turnoRepository.findById(id);
            turnoSet.add(turnoEncontrado.get());
        }
        paciente.setTurnoSet(turnoSet);

        return paciente;
    }

    public PacienteDTO guardarPaciente (PacienteDTO paciente) {
        Paciente pacienteAGuardar = pacienteDTOaPaciente(paciente);
        Paciente pacienteGuardado = pacienteRepository.save(pacienteAGuardar);
        return pacienteAPacienteDTO(pacienteGuardado);
    }
    public Optional<PacienteDTO> buscarPaciente(Long id) {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        return pacienteBuscado.map(this::pacienteAPacienteDTO);
    }
    public Optional<PacienteDTO> buscarPorNombreYApellido(String nombre, String apellido) {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findByNombreAndApellido(nombre, apellido);
        return pacienteBuscado.map(this::pacienteAPacienteDTO);
    }
    public Optional<PacienteDTO> buscarPorEmail(String email) {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findByEmail(email);
        return pacienteBuscado.map(this::pacienteAPacienteDTO);
    }
    public List<PacienteDTO> mostrarPacientes() {
        List<Paciente> pacientesEncontrados = pacienteRepository.findAll();
        List<PacienteDTO> respuesta = new ArrayList<>();
        for (Paciente p: pacientesEncontrados) {
            respuesta.add(pacienteAPacienteDTO(p));
        }
        return respuesta;
    }
    public void actualizarPaciente(PacienteDTO paciente) {
        Paciente pacienteAActualizar = pacienteDTOaPaciente(paciente);
        pacienteRepository.save(pacienteAActualizar);
    }
    public void eliminarPaciente(Long id) { pacienteRepository.deleteById(id); }




//    public PacienteDTO guardarPaciente (PacienteDTO paciente) { return pacienteRepository.save(paciente); }
//    public Optional<PacienteDTO> buscarPorId(Long id) { return pacienteRepository.findById(id); }
//    public Optional<PacienteDTO> buscarPorNombreYApellido(String nombre, String apellido) { return pacienteRepository.findByNombreAndApellido(nombre, apellido); }
//    public Optional<PacienteDTO> buscarPorEmail(String email) { return pacienteRepository.findByEmail(email); }
//    public List<PacienteDTO> mostrarPacientes() { return pacienteRepository.findAll(); }
//    public void actualizarPaciente(PacienteDTO paciente) { pacienteRepository.save(paciente); }
//    public void eliminarPaciente(Long id) { pacienteRepository.deleteById(id); }

}
