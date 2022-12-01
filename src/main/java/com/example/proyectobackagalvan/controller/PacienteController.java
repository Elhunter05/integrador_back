package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.dto.PacienteDTO;
import com.example.proyectobackagalvan.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> guardarPaciente(@RequestBody PacienteDTO paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable("id") Long id) {
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPaciente(id);
        ResponseEntity<PacienteDTO> response;

        response = pacienteBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;

//        if (pacienteBuscado.isPresent()) {
//            response = ResponseEntity.ok(pacienteBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
//        return response;
    }

    @GetMapping("/buscar-nombre-completo/{nombre}-{apellido}")
    public ResponseEntity<PacienteDTO> buscarPorNombreYApellido(@PathVariable(name = "nombre") String nombre, @PathVariable(name = "apellido") String apellido) {
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPorNombreYApellido(nombre, apellido);
        ResponseEntity<PacienteDTO> response;

        response = pacienteBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;

//        if (pacienteBuscado.isPresent()) {
//            response = ResponseEntity.ok(pacienteBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
//        return response;
    }

    @GetMapping("/buscar-email/{email}")
    public ResponseEntity<PacienteDTO> buscarPorEmail(@PathVariable("email") String email) {
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPorEmail(email);
        ResponseEntity<PacienteDTO> response;

        response = pacienteBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;

//        if (pacienteBuscado.isPresent()) {
//            response = ResponseEntity.ok(pacienteBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
//        return response;
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> mostrarPacientes() {
        return ResponseEntity.ok(pacienteService.mostrarPacientes());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody PacienteDTO paciente){
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        ResponseEntity<String> response;

        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            response = ResponseEntity.ok("Se actualizó el paciente con apellido " + paciente.getApellido());
        } else {
            response = ResponseEntity.badRequest().body("El paciente con id= " + paciente.getId() + " no existe en la base de datos");
        }
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPaciente(id);
        ResponseEntity<String> response;

        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(pacienteBuscado.get().getId());
            response = ResponseEntity.ok().body("El paciente fue eliminado");
        } else {
            response = ResponseEntity.badRequest().body("No se puede eliminar al paciente con id=" + id + ", ya que el mismo no existe en la base de datos");
        }
        return response;
    }
}