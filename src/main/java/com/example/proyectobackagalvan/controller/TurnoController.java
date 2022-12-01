package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.dto.OdontologoDTO;
import com.example.proyectobackagalvan.dto.PacienteDTO;
import com.example.proyectobackagalvan.dto.TurnoDTO;
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
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody TurnoDTO turno) {
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPaciente(turno.getPacienteId());
        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologoId());
        ResponseEntity<TurnoDTO> response;

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPacienteId(pacienteBuscado.get().getId());
            turno.setOdontologoId(odontologoBuscado.get().getId());
            response = ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable("id") Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        ResponseEntity<TurnoDTO> response;

        response = turnoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;

//        if(turnoBuscado.isPresent()){
//            response = ResponseEntity.ok(turnoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
//        return response;
    }

    @GetMapping("/buscar-odontologo/{id}")
    public ResponseEntity<List<TurnoDTO>> buscarPorOdontologo(@PathVariable Long id) {
        Optional<List<TurnoDTO>> turnosOdontologo = Optional.ofNullable(turnoService.buscarPorOdontologo(id));

        return turnosOdontologo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

//        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
//
//        if(odontologoBuscado.isPresent()) {
//            Optional<Set<Turno>> turnosOdontologo = turnoService.buscarPorOdontologo(odontologoBuscado.get());
//            if (turnosOdontologo.isPresent()) {
//                return ResponseEntity.ok(turnosOdontologo.get());
//            }
//        }
//        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscar-paciente/{id}")
    public ResponseEntity<List<TurnoDTO>> buscarPorPaciente(@PathVariable Long id) {
        Optional<List<TurnoDTO>> turnosPaciente = Optional.ofNullable(turnoService.buscarPorPaciente(id));

        return turnosPaciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

//        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
//
//        if(pacienteBuscado.isPresent()) {
//            Optional<Set<Turno>> turnosPaciente = turnoService.buscarPorPaciente(pacienteBuscado.get());
//            if (turnosPaciente.isPresent()) {
//                return ResponseEntity.ok(turnosPaciente.get());
//            }
//        }
//        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> mostrarTurnos() {
        return ResponseEntity.ok(turnoService.mostrarTurnos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno){
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
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
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