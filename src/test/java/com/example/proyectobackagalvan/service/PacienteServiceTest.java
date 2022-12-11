package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
                new Domicilio("Calle a",548,"Salta Capital","Salta"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        assertEquals(1L, pacienteGuardado.getId());
        assertEquals("Andrés", pacienteGuardado.getNombre());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest() throws ResourceNotFoundException {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);

        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarPorEmailTest() throws ResourceNotFoundException {
        String email = "8il.andre@gmail.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);

        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(4)
    public void buscarPorNombreYApellidoTest() {
        String nombre = "Andrés";
        String apellido = "Galván";
        List<Paciente> pacienteBuscado = pacienteService.buscarPacientesPorNombreYApellido(nombre, apellido);

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
    public void actualizarPacienteTest() throws ResourceNotFoundException {
        Paciente pacienteAActualizar = new Paciente(1L, "Javi","Grande", "8888", "prueba@gmail.com", LocalDate.of(1950,11,30),
                new Domicilio("Calle a",548,"Salta Capital","Salta"));
        pacienteService.guardarPaciente(pacienteAActualizar);
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());

        assertEquals("Javi", pacienteActualizado.get().getNombre());
        assertEquals(1, pacienteActualizado.get().getId());
    }

    @Test
    @Order(7)
    public void eliminarPacienteTest() throws ResourceNotFoundException  {
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> pacienteService.buscarPaciente(idAEliminar)
        );

        assertTrue(thrown.getMessage().contains("No se encontró ningún paciente con id="+idAEliminar));
    }
}