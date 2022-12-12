package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.exception.BadRequestException;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    ITurnoService turnoService;

    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarTurnoTest() throws BadRequestException, ResourceNotFoundException {
        Paciente pacienteAGuardar = pacienteService.guardarPaciente(new Paciente("Andrés","Galván", "4900", "8il.andre@gmail.com", LocalDate.of(2022,12,12),
                new Domicilio("Calle a",548,"Salta Capital","Salta")));
        Odontologo odontologoAGuardar = odontologoService.guardarOdontologo(new Odontologo(12345, "Andrés", "Galván"));

        TurnoDTO turnoDTOAGuardar = new TurnoDTO();
        turnoDTOAGuardar.setPacienteId(pacienteAGuardar.getId());
        turnoDTOAGuardar.setOdontologoId(odontologoAGuardar.getId());
        turnoDTOAGuardar.setFecha(LocalDate.of(2022,12,12));
        TurnoDTO turnoDTOGuardado = turnoService.guardarTurno(turnoDTOAGuardar);

        assertEquals(1L, turnoDTOGuardado.getId());
        assertEquals(LocalDate.of(2022,12,12), turnoDTOGuardado.getFecha());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() throws ResourceNotFoundException, BadRequestException {
        Long idABuscar = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(idABuscar);

        assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    public void buscarPorOdontologoTest() {
        Long idABuscar = 1L;
        List<TurnoDTO> turnosOdontologoBuscado = turnoService.buscarPorOdontologo(idABuscar);

        assertNotNull(turnosOdontologoBuscado);
    }

    @Test
    @Order(4)
    public void buscarPorPacienteTest() {
        Long idABuscar = 1L;
        List<TurnoDTO> turnosPacientelogoBuscado = turnoService.buscarPorPaciente(idABuscar);

        assertNotNull(turnosPacientelogoBuscado);
    }

    @Test
    @Order(5)
    public void mostrarTurnosTest() {
        List<TurnoDTO> turnos = turnoService.mostrarTurnos();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, turnos.size());
    }

    @Test
    @Order(6)
    public void actualizarTurnoTest() throws ResourceNotFoundException, BadRequestException {
        Paciente pacienteAActualizar = pacienteService.guardarPaciente(new Paciente("Javi","Grande", "9900", "actualizate@gmail.com", LocalDate.of(1999,9,15),
                new Domicilio("Calle b",111,"Buenos Aires","Buenos Aires")));
        Odontologo odontologoAActualizar = odontologoService.guardarOdontologo(new Odontologo(999, "Javi", "Grande"));
        LocalDate nuevaFecha = LocalDate.of(1995,5,29);

        TurnoDTO turnoDTOAActualizar = new TurnoDTO();
        turnoDTOAActualizar.setId(1L);
        turnoDTOAActualizar.setPacienteId(pacienteAActualizar.getId());
        turnoDTOAActualizar.setOdontologoId(odontologoAActualizar.getId());
        turnoDTOAActualizar.setFecha(nuevaFecha);
        turnoService.guardarTurno(turnoDTOAActualizar);
        turnoService.actualizarTurno(turnoDTOAActualizar);
        Optional<TurnoDTO> turnoDTOActualizado = turnoService.buscarTurno(turnoDTOAActualizar.getId());

        assertEquals(nuevaFecha, turnoDTOActualizado.get().getFecha());
    }

    @Test
    @Order(7)
    public void eliminarTurnoTest() throws ResourceNotFoundException, BadRequestException {
        Long idAEliminar = 1L;
        turnoService.eliminarTurno(idAEliminar);

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> turnoService.buscarTurno(idAEliminar).isPresent()
        );

        assertTrue(thrown.getMessage().contains("No se encontró ningún turno con id="+idAEliminar));
    }
}