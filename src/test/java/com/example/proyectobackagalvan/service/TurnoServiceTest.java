package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.entity.Turno;
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
    public void guardarTurnoTest() throws ResourceNotFoundException, BadRequestException {
        Paciente pacienteAGuardar = pacienteService.guardarPaciente(new Paciente("Andrés","Galván", "4900", "8il.andre@gmail.com", LocalDate.of(2022,12,12),
                new Domicilio("Calle a",548,"Salta Capital","Salta"), new HashSet<>()));
        Odontologo odontologoAGuardar = odontologoService.guardarOdontologo(new Odontologo(12345, "Andrés", "Galván", new HashSet<>()));

        TurnoDTO turnoDTOAGuardar = new TurnoDTO();
        turnoDTOAGuardar.setPacienteId(pacienteAGuardar.getId());
        turnoDTOAGuardar.setOdontologoId(odontologoAGuardar.getId());
        turnoDTOAGuardar.setFecha(LocalDate.of(2022,12,12));
        turnoService.guardarTurno(turnoDTOAGuardar);

        assertEquals(1L, turnoDTOAGuardar.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() throws ResourceNotFoundException {
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
    public void actualizarTurnoTest() throws ResourceNotFoundException {
        TurnoDTO turnoAActualizar = new TurnoDTO();

        Turno turnoDeOdontologo = new Turno(new Paciente(), new Odontologo(), LocalDate.of(2022,12,12));

        Odontologo odontologoAActualizar = new Odontologo(12345, "Andrés", "Galván",
                new HashSet<Turno>((Collection<? extends Turno>) turnoDeOdontologo));
        odontologoService.guardarOdontologo(odontologoAActualizar);
        turnoAActualizar.setOdontologoId(odontologoAActualizar.getId());

        Paciente pacienteAActualizar = new Paciente("Andrés","Galván", "4900", "8il.andre@gmail.com", LocalDate.of(2022,12,12),
                new Domicilio("Calle a",548,"Salta Capital","Salta"),
                new HashSet<Turno>((Collection<? extends Turno>) turnoDeOdontologo));
        pacienteService.guardarPaciente(pacienteAActualizar);
        turnoAActualizar.setPacienteId(pacienteAActualizar.getId());

        LocalDate nuevaFecha = LocalDate.of(2000,1,1);
        turnoAActualizar.setFecha(nuevaFecha);
        turnoService.actualizarTurno(turnoAActualizar);
        Optional<TurnoDTO> turnoActualizado = turnoService.buscarTurno(turnoAActualizar.getId());

        assertEquals(nuevaFecha, turnoActualizado.get().getFecha());
    }

    @Test
    @Order(7)
    public void eliminarTurnoTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);

        assertFalse(turnoEliminado.isPresent());
    }
}