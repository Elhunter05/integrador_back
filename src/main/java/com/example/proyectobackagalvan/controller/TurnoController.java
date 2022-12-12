package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.exception.BadRequestException;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import com.example.proyectobackagalvan.service.OdontologoService;
import com.example.proyectobackagalvan.service.PacienteService;
import com.example.proyectobackagalvan.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
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
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable("id") Long id) throws ResourceNotFoundException, BadRequestException {
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
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException, BadRequestException {
        turnoService.actualizarTurno(turno);
        return ResponseEntity.ok("Se actualizó el turno con id=" + turno.getId());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException, BadRequestException {
        turnoService.eliminarTurno(id);
        return  ResponseEntity.ok().body("El turno se ha eliminado");
    }
}