package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
@CrossOrigin("*")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Odontologo> buscarPorId(@RequestParam Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        ResponseEntity<Odontologo> response;

//        if (odontologoBuscado.isPresent()) {
//            response = ResponseEntity.ok(odontologoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
        response = odontologoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @GetMapping("/buscar-nombre-completo")
    public ResponseEntity<Odontologo> buscarPorNombreYApellido(@RequestParam String nombre, String apellido) {

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorNombreYApellido(nombre, apellido);
        ResponseEntity<Odontologo> response;

//        if (odontologoBuscado.isPresent()) {
//            response = ResponseEntity.ok(odontologoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
        response = odontologoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @GetMapping("/buscar-matricula")
    public ResponseEntity<Odontologo> buscarPorMatricula(@RequestParam Integer matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        ResponseEntity<Odontologo> response;

//        if (odontologoBuscado.isPresent()) {
//            response = ResponseEntity.ok(odontologoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
        response = odontologoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarOdontologos(){
        return ResponseEntity.ok(odontologoService.mostrarOdontologos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
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
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        ResponseEntity<String> response;

        if (odontologoBuscado.isPresent()) {
            odontologoService.eliminarOdontologo(odontologoBuscado.get().getId());
            response = ResponseEntity.ok().body("El odontólogo fue eliminado");
        } else {
            response = ResponseEntity.badRequest().body("No se puede eliminar al odontólogo con id=" + id + ", ya que el mismo no existe en la base de datos");
        }
        return response;
    }
}
