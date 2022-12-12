package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import com.example.proyectobackagalvan.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id).get());
    }

    @GetMapping("/buscar-email/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable("email") String email) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscarPorEmail(email).get());
    }

    @GetMapping("/buscar-nombre-completo/{nombre}-{apellido}")
    public ResponseEntity<List<Paciente>> buscarPacientesPorNombreYApellido(@PathVariable(name = "nombre") String nombre, @PathVariable(name = "apellido") String apellido) {
        Optional<List<Paciente>> pacientesBuscadoList = Optional.ofNullable(pacienteService.buscarPacientesPorNombreYApellido(nombre, apellido));
        return pacientesBuscadoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> mostrarPacientes() {
        Optional<List<Paciente>> pacientesList = Optional.ofNullable(pacienteService.mostrarPacientes());
        return pacientesList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok("Se actualizó al paciente con id="+paciente.getId());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok().body("El paciente con id=" + id + " fue eliminado");
    }
}