package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.service.OdontologoService;
import com.example.proyectobackagalvan.service.PacienteService;
import com.example.proyectobackagalvan.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable("id") Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        ResponseEntity<Turno> response;

        if(turnoBuscado.isPresent()){
            response = ResponseEntity.ok(turnoBuscado.get());
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        ResponseEntity<Turno> response;

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(turno.getId());
        ResponseEntity<String> response;

        if (turnoBuscado.isPresent()) {
            if (pacienteService.buscarPaciente(turno.getPaciente().getId()).isPresent() && odontologoService.buscarOdontologo(turno.getOdontologo().getId()) .isPresent()) {
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
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        ResponseEntity<String> response;

        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            response = ResponseEntity.ok().body("El turno se ha eliminado");
        } else {
            response = ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND + "\n" + "No se puede eliminar el turno con id=" + id + ", ya que el mismo no existe en la base de datos");
        }
        return response;
    }
}