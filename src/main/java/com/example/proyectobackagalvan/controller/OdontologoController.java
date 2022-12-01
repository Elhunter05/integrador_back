package com.example.proyectobackagalvan.controller;

import com.example.proyectobackagalvan.dto.OdontologoDTO;
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
    public ResponseEntity<OdontologoDTO> guardarOdontologo(@RequestBody OdontologoDTO odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<OdontologoDTO> buscarPorId(@RequestParam Long id) {
        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarOdontologo(id);
        ResponseEntity<OdontologoDTO> response;

//        if (odontologoBuscado.isPresent()) {
//            response = ResponseEntity.ok(odontologoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
        response = odontologoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @GetMapping("/buscar-nombre-completo")
    public ResponseEntity<OdontologoDTO> buscarPorNombreYApellido(@RequestParam String nombre, String apellido) {

        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarPorNombreYApellido(nombre, apellido);
        ResponseEntity<OdontologoDTO> response;

//        if (odontologoBuscado.isPresent()) {
//            response = ResponseEntity.ok(odontologoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
        response = odontologoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @GetMapping("/buscar-matricula")
    public ResponseEntity<OdontologoDTO> buscarPorMatricula(@RequestParam Integer matricula) {
        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        ResponseEntity<OdontologoDTO> response;

//        if (odontologoBuscado.isPresent()) {
//            response = ResponseEntity.ok(odontologoBuscado.get());
//        } else {
//            response = ResponseEntity.notFound().build();
//        }
        response = odontologoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> mostrarOdontologos(){
        return ResponseEntity.ok(odontologoService.mostrarOdontologos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody OdontologoDTO odontologo){
        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
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
        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarOdontologo(id);
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
