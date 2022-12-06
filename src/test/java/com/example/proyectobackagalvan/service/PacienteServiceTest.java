package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Paciente;
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
class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest() {
        Paciente pacienteAGuardar = new Paciente("Andrés","Galván", "4900", "8il.andre@gmail.com", LocalDate.of(2022,12,12),
                new Domicilio("Calle a",548,"Salta Capital","Salta"), new HashSet<>());
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);

        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(3)
    public void buscarPorNombreYApellidoTest() {
        String nombre = "Andrés";
        String apellido = "Galván";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorNombreYApellido(nombre, apellido);

        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(4)
    public void buscarPorEmailTest() {
        String email = "8il.andre@gmail.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);

        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(5)
    public void mostrarPacientesTest() {
        List<Paciente> pacientes = pacienteService.mostrarPacientes();
        Integer cantidadEsperada = 1;

        assertEquals(cantidadEsperada, pacientes.size());
    }

    @Test
    @Order(6)
    public void actualizarPacienteTest() {
        Paciente pacienteAActualizar = new Paciente("Javi","Grande", "8888", "prueba@gmail.com", LocalDate.of(1950,11,30),
                new Domicilio("Calle a",548,"Salta Capital","Salta"), new HashSet<>());
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());

        assertEquals("Javi", pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(7)
    public void eliminarPacienteTest() {
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);

        assertFalse(pacienteEliminado.isPresent());
    }
}