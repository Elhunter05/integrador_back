package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
        Odontologo odontologoAGuardar = new Odontologo();

        odontologoAGuardar.setMatricula(1999);
        odontologoAGuardar.setNombre("Andrés");
        odontologoAGuardar.setApellido("Galván");

        HashSet<Turno> turnoSetDeOdontologo = new HashSet<>();
        Turno turnoDeOdontologo = new Turno(new Paciente(), odontologoAGuardar, LocalDate.of(1850, 11, 30));
        turnoSetDeOdontologo.add(turnoDeOdontologo);
        odontologoAGuardar.setTurnoSet(turnoSetDeOdontologo);

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);

        assertEquals(1L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() {
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(idABuscar);

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
        Integer matricula = 1234;

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
        Odontologo odontologoAActualizar = new Odontologo();
        odontologoAActualizar.setId(1L);
        odontologoAActualizar.setMatricula(999);
        odontologoAActualizar.setNombre("Javi");
        odontologoAActualizar.setApellido("Grande");
        odontologoAActualizar.setTurnoSet(new HashSet<>());

        odontologoService.actualizarOdontologo(odontologoAActualizar);

        Optional<Odontologo> odontologoActualizado = odontologoService.buscarPorId(odontologoAActualizar.getId());

        assertEquals("Javi", odontologoActualizado.get().getNombre());
    }

    @Test
    @Order(7)
    public void eliminarOdontologoTest() {
        Long idAEliminar = 1L;

        odontologoService.eliminarOdontologo(idAEliminar);

        Optional<Odontologo> odontologoEliminado = odontologoService.buscarPorId(idAEliminar);

        assertFalse(odontologoEliminado.isPresent());
    }

}