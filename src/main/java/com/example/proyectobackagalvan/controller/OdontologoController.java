package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import com.example.proyectobackagalvan.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@RequestParam Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscarOdontologo(id).get());
    }

    @GetMapping("/buscar-matricula")
    public ResponseEntity<Odontologo> buscarPorMatricula(@RequestParam Integer matricula) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula).get());
    }

    @GetMapping("/buscar-nombre-completo")
    public ResponseEntity<List<Odontologo>> buscarOdontologosPorNombreYApellido(@RequestParam String nombre, String apellido) {
        Optional<List<Odontologo>> odontologosBuscadoList = Optional.ofNullable(odontologoService.buscarOdontologosPorNombreYApellido(nombre, apellido));
        return odontologosBuscadoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarOdontologos(){
        Optional<List<Odontologo>> odontologosList = Optional.ofNullable(odontologoService.mostrarOdontologos());
        return odontologosList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok("Se actualizó al odontólogo con id="+odontologo.getId());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok().body("El odontólogo con id=" + id + " fue eliminado");
    }
}
