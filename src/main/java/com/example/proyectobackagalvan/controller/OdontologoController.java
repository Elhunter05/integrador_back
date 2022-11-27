package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odontologos")
@CrossOrigin("*")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<Odontologo> buscarOdontologo(@RequestParam Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        ResponseEntity<Odontologo> response;

        if (odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(odontologoBuscado.get());
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @GetMapping("/buscar-nombre-completo")
    public ResponseEntity<Odontologo> buscarOdontologoByNombreCompleto(@RequestParam String nombre, String apellido) {

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorNombreCompleto(nombre, apellido);
        ResponseEntity<Odontologo> response;

        if (odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(odontologoBuscado.get());
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @GetMapping("/buscar-matricula")
    public ResponseEntity<Odontologo> buscarOdontologoPorMatricula(@RequestParam Integer matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(matricula);
        ResponseEntity<Odontologo> response;

        if (odontologoBuscado.isPresent()) {
            response = ResponseEntity.ok(odontologoBuscado.get());
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
        ResponseEntity<String> response;

        if (odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            response = ResponseEntity.ok("Se actualizó al odontólogo con matrícula " + odontologo.getMatricula());
        } else {
            response = ResponseEntity.badRequest().body("El odontólogo con matrícula= " + odontologo.getMatricula() + " no existe en la base de datos");
        }
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        ResponseEntity<String> response;

        if (odontologoBuscado.isPresent()) {
            odontologoService.eliminarOdontologo(odontologoBuscado.get().getId());
            response = ResponseEntity.ok().body("El odontólogo fue eliminado");
        } else {
            response = ResponseEntity.badRequest().body("No se puede eliminar al odontólogo con matrícula=" + odontologoBuscado.get().getMatricula() + ", ya que el mismo no existe en la base de datos");
        }
        return response;
    }
}
