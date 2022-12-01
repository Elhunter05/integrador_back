package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.OdontologoDTO;
import com.example.proyectobackagalvan.dto.PacienteDTO;
import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Domicilio;
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
    public void guardarTurnoTest() {
        TurnoDTO turnoAGuardar = new TurnoDTO();

        OdontologoDTO odontologoAGuardar = new OdontologoDTO();
        odontologoAGuardar.setMatricula(1999);
        odontologoAGuardar.setNombre("Andrés");
        odontologoAGuardar.setApellido("Galván");
        odontologoAGuardar.setTurnoIdList(new ArrayList<>());


        odontologoService.guardarOdontologo(odontologoAGuardar);
        turnoAGuardar.setOdontologoId(odontologoAGuardar.getId());

        PacienteDTO pacienteAGuardar = new PacienteDTO();
        pacienteAGuardar.setNombre("Andrés");
        pacienteAGuardar.setApellido("Galván");
        pacienteAGuardar.setDni("4900");
        pacienteAGuardar.setEmail("8il.andre@gmail.com");
        pacienteAGuardar.setFechaIngreso(LocalDate.of(1995, 5, 29));
        pacienteAGuardar.setDomicilioId(new Domicilio("Falsa", 123, "Sayago", "Montevideo").getId());
        pacienteAGuardar.setTurnoIdList(new ArrayList<>());
        pacienteService.guardarPaciente(pacienteAGuardar);
        turnoAGuardar.setPacienteId(pacienteAGuardar.getId());

        turnoAGuardar.setFecha(LocalDate.of(1995,5,29));

        TurnoDTO turnoGuardado = turnoService.guardarTurno(turnoAGuardar);

        assertEquals(1L, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() {
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
    public void actualizarTurnoTest() {
        TurnoDTO turnoAActualizar = new TurnoDTO();

        OdontologoDTO odontologoAActualizar = new OdontologoDTO();
        odontologoAActualizar.setId(1L);
        odontologoAActualizar.setMatricula(999);
        odontologoAActualizar.setNombre("Javi");
        odontologoAActualizar.setApellido("Grande");
        odontologoAActualizar.setTurnoIdList(new ArrayList<>());
        odontologoService.guardarOdontologo(odontologoAActualizar);
        turnoAActualizar.setOdontologoId(odontologoAActualizar.getId());

        PacienteDTO pacienteAActualizar = new PacienteDTO();
        pacienteAActualizar.setId(1L);
        pacienteAActualizar.setNombre("Javi");
        pacienteAActualizar.setApellido("Grande");
        pacienteAActualizar.setDni("999");
        pacienteAActualizar.setEmail("grandeJavi@gmail.com");
        pacienteAActualizar.setFechaIngreso(LocalDate.of(1999, 9, 29));
        pacienteAActualizar.setDomicilioId(new Domicilio("Corrientes", 4, "Ushuaia", "Argentina").getId());
        pacienteAActualizar.setTurnoIdList(new ArrayList<>());
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
    public void eliminarTurnoTest() {
        Long idAEliminar = 1L;

        turnoService.eliminarTurno(idAEliminar);

        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);

        assertFalse(turnoEliminado.isPresent());
    }







//    @Test
//    @Order(1)
//    public void guardarTurnoTest() {
//        Turno turnoAGuardar = new Turno();
//
//        Odontologo odontologoAGuardar = new Odontologo();
//        odontologoAGuardar.setMatricula(1999);
//        odontologoAGuardar.setNombre("Andrés");
//        odontologoAGuardar.setApellido("Galván");
//        odontologoAGuardar.setTurnoSet(new HashSet<>());
//        odontologoService.guardarOdontologo(odontologoAGuardar);
//        turnoAGuardar.setOdontologo(odontologoAGuardar);
//
//        Paciente pacienteAGuardar = new Paciente();
//        pacienteAGuardar.setNombre("Andrés");
//        pacienteAGuardar.setApellido("Galván");
//        pacienteAGuardar.setDni("4900");
//        pacienteAGuardar.setEmail("8il.andre@gmail.com");
//        pacienteAGuardar.setFechaIngreso(LocalDate.of(1995, 5, 29));
//        pacienteAGuardar.setDomicilio(new Domicilio("Falsa", 123, "Sayago", "Montevideo"));
//        pacienteAGuardar.setTurnoSet(new HashSet<>());
//        pacienteService.guardarPaciente(pacienteAGuardar);
//        turnoAGuardar.setPaciente(pacienteAGuardar);
//
//        turnoAGuardar.setFecha(LocalDate.of(1995,5,29));
//
//        Turno turnoGuardado = turnoService.guardarTurno(turnoAGuardar);
//
//        assertEquals(1L, turnoGuardado.getId());
//    }
//
//    @Test
//    @Order(2)
//    public void buscarPorIdTest() {
//        Long idABuscar = 1L;
//        Optional<Turno> turnoBuscado = turnoService.buscarPorId(idABuscar);
//
//        assertNotNull(turnoBuscado);
//    }
//
//    @Test
//    @Order(3)
//    public void buscarPorOdontologoTest() {
//        Long idABuscar = 1L;
//        Optional<Set<Turno>> turnosOdontologoBuscado = turnoService.buscarPorOdontologoId(idABuscar);
//
//        assertNotNull(turnosOdontologoBuscado);
//    }
//
//    @Test
//    @Order(4)
//    public void buscarPorPacienteTest() {
//        Long idABuscar = 1L;
//        Optional<Set<Turno>> turnosPacientelogoBuscado = turnoService.buscarPorPaciente(idABuscar);
//
//        assertNotNull(turnosPacientelogoBuscado);
//    }
//
//    @Test
//    @Order(5)
//    public void mostrarTurnosTest() {
//        List<Turno> turnos = turnoService.mostrarTurnos();
//        Integer cantidadEsperada = 1;
//        assertEquals(cantidadEsperada, turnos.size());
//    }
//
//    @Test
//    @Order(6)
//    public void actualizarTurnoTest() {
//        Turno turnoAActualizar = new Turno();
//
//        Odontologo odontologoAActualizar = new Odontologo();
//        odontologoAActualizar.setId(1L);
//        odontologoAActualizar.setMatricula(999);
//        odontologoAActualizar.setNombre("Javi");
//        odontologoAActualizar.setApellido("Grande");
//        odontologoAActualizar.setTurnoSet(new HashSet<>());
//        odontologoService.guardarOdontologo(odontologoAActualizar);
//        turnoAActualizar.setOdontologo(odontologoAActualizar);
//
//        Paciente pacienteAActualizar = new Paciente();
//        pacienteAActualizar.setId(1L);
//        pacienteAActualizar.setNombre("Javi");
//        pacienteAActualizar.setApellido("Grande");
//        pacienteAActualizar.setDni("999");
//        pacienteAActualizar.setEmail("grandeJavi@gmail.com");
//        pacienteAActualizar.setFechaIngreso(LocalDate.of(1999, 9, 29));
//        pacienteAActualizar.setDomicilio(new Domicilio("Corrientes", 4, "Ushuaia", "Argentina"));
//        pacienteAActualizar.setTurnoSet(new HashSet<>());
//        pacienteService.guardarPaciente(pacienteAActualizar);
//        turnoAActualizar.setPaciente(pacienteAActualizar);
//
//        LocalDate nuevaFecha = LocalDate.of(2000,1,1);
//        turnoAActualizar.setFecha(nuevaFecha);
//
//        turnoService.actualizarTurno(turnoAActualizar);
//
//        Optional<Turno> turnoActualizado = turnoService.buscarTurno(turnoAActualizar.getId());
//
//        assertEquals(nuevaFecha, turnoActualizado.get().getFecha());
//    }
//
//    @Test
//    @Order(7)
//    public void eliminarTurnoTest() {
//        Long idAEliminar = 1L;
//
//        turnoService.eliminarTurno(idAEliminar);
//
//        Optional<Turno> turnoEliminado = turnoService.buscarPorId(idAEliminar);
//
//        assertFalse(turnoEliminado.isPresent());
//    }

}