package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.exception.BadRequestException;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import com.example.proyectobackagalvan.service.OdontologoService;
import com.example.proyectobackagalvan.service.PacienteService;
import com.example.proyectobackagalvan.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@CrossOrigin("*")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException, BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPacienteId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologoId());
        ResponseEntity<TurnoDTO> response;

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPacienteId(pacienteBuscado.get().getId());
            turno.setOdontologoId(odontologoBuscado.get().getId());
            response = ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            throw new BadRequestException("Por favor revise que los datos del paciente y odontólogo sean correctos");
        }
        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.buscarTurno(id).get());
    }

    @GetMapping("/buscar-odontologo/{id}")
    public ResponseEntity<List<TurnoDTO>> buscarPorOdontologo(@PathVariable Long id) {
        Optional<List<TurnoDTO>> turnosOdontologo = Optional.ofNullable(turnoService.buscarPorOdontologo(id));
        return turnosOdontologo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar-paciente/{id}")
    public ResponseEntity<List<TurnoDTO>> buscarPorPaciente(@PathVariable Long id) {
        Optional<List<TurnoDTO>> turnosPaciente = Optional.ofNullable(turnoService.buscarPorPaciente(id));
        return turnosPaciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> mostrarTurnos() {
        Optional<List<TurnoDTO>> turnosList = Optional.ofNullable(turnoService.mostrarTurnos());
        return turnosList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turno.getId());
        ResponseEntity<String> response;

        if (turnoBuscado.isPresent()) {
            if (pacienteService.buscarPaciente(turno.getPacienteId()).isPresent() && odontologoService.buscarOdontologo(turno.getOdontologoId()).isPresent()) {
                turnoService.actualizarTurno(turno);
                response = ResponseEntity.ok("Se actualizó el turno con id=" + turno.getId());
            } else {
                response = ResponseEntity.badRequest().body("Error al actualizar, verificar si el odontólogo y/o el paciente existen en la base de datos");
            }
        } else {
            response = ResponseEntity.badRequest().body("No se puede actualizar un turno que no exista en la base de datos");
        }
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return  ResponseEntity.ok().body("El turno se ha eliminado");
    }
}