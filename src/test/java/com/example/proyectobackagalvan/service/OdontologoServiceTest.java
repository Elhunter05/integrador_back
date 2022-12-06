package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest() {
        Odontologo odontologoAGuardar = new Odontologo(12345, "Andrés", "Galván", new HashSet<>());
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);

        assertEquals(1L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() {
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);

        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    public void buscarPorNombreYApellidoTest() {
        String nombre = "Andrés";
        String apellido = "Galván";
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorNombreYApellido(nombre, apellido);

        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(4)
    public void buscarPorMatriculaTest() {
        Integer matricula = 12345;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);

        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(5)
    public void mostrarOdontologosTest() {
        List<Odontologo> odontologos = odontologoService.mostrarOdontologos();
        Integer cantidadEsperada = 1;

        assertEquals(cantidadEsperada, odontologos.size());
    }

    @Test
    @Order(6)
    public void actualizarOdontologoTest() {
        Odontologo odontologoAActualizar = new Odontologo(888, "Javi", "Grande", new HashSet<>());
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        Optional<Odontologo> odontologoActualizado = odontologoService.buscarOdontologo(odontologoAActualizar.getId());

        assertEquals("Javi", odontologoActualizado.get().getNombre());
    }

    @Test
    @Order(7)
    public void eliminarOdontologoTest() {
        Long idAEliminar = 1L;
        odontologoService.eliminarOdontologo(idAEliminar);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologo(idAEliminar);

        assertFalse(odontologoEliminado.isPresent());
    }
}