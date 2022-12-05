package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.PacienteDTO;
import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.repository.DomicilioRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    ITurnoService turnoService;

    @Autowired
    DomicilioRepository domicilioRepository;

    @Test
    @Order(1)
    public void guardarPacienteTest() {
        // Recibe un pacienteDTO, crea un paciente y usa PacienteDTOtoPaciente
        // Guarda al paciente y antes del return lo vuelve a transformar de paciente a pacienteDTO
        PacienteDTO pacienteAGuardar = new PacienteDTO();

        pacienteAGuardar.setNombre("Andrés");
        pacienteAGuardar.setApellido("Galván");
        pacienteAGuardar.setDni("4900");
        pacienteAGuardar.setEmail("8il.andre@gmail.com");
        pacienteAGuardar.setFechaIngreso(LocalDate.of(1995, 5, 29));

        Domicilio domicilio = new Domicilio("Falsa", 123, "Sayago", "Montevideo");
        domicilioRepository.save(domicilio);
        pacienteAGuardar.setDomicilioId(domicilio.getId());

        List<Long> turnoIdListDePaciente = new ArrayList<>();
        TurnoDTO turnoDePaciente = new TurnoDTO();

        turnoDePaciente.setId(1L);
        Paciente pacienteDeTurnoList = new Paciente();
        turnoDePaciente.setPacienteId(pacienteDeTurnoList.getId());
        Odontologo odontologoDeTurnoList = new Odontologo();
        odontologoService.guardarOdontologo(odontologoDeTurnoList);
        turnoDePaciente.setOdontologoId(odontologoDeTurnoList.getId());
        turnoDePaciente.setFecha(LocalDate.of(1999, 9, 29));
        turnoService.guardarTurno(turnoDePaciente);

        turnoIdListDePaciente.add(turnoDePaciente.getPacienteId());

        pacienteAGuardar.setTurnoIdList(turnoIdListDePaciente);


        // El problema es que PacienteService no guarda un set, por lo que la conversión falla. Debo arreglar eso primero.
        PacienteDTO pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdTest() {
        Long idABuscar = 1L;
        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);

        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(3)
    public void buscarPorNombreYApellidoTest() {
        String nombre = "Andrés";
        String apellido = "Galván";

        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPorNombreYApellido(nombre, apellido);

        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(4)
    public void buscarPorEmailTest() {
        String email = "8il.andre@gmail.com";

        Optional<PacienteDTO> pacienteBuscado = pacienteService.buscarPorEmail(email);

        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(5)
    public void mostrarPacientesTest() {
        List<PacienteDTO> pacientes = pacienteService.mostrarPacientes();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, pacientes.size());
    }

    @Test
    @Order(6)
    public void actualizarPacienteTest() {
        // Creo un nuevo paciente con los nuevos datos. Le hardcodeo el ID para que sea el mismo al paciente viejo así sea da cuenta que lo debe sobreescribir
        PacienteDTO pacienteAActualizar = new PacienteDTO();
        pacienteAActualizar.setId(1L);
        pacienteAActualizar.setNombre("Javi");
        pacienteAActualizar.setApellido("Grande");
        pacienteAActualizar.setDni("999");
        pacienteAActualizar.setEmail("grandeJavi@gmail.com");
        pacienteAActualizar.setFechaIngreso(LocalDate.of(1999, 9, 29));
        pacienteAActualizar.setDomicilioId(new Domicilio("Corrientes", 4, "Ushuaia", "Argentina").getId());
        pacienteAActualizar.setTurnoIdList(new ArrayList<>());

        // Acá debería reconocer que el id=1 ya está en uso y sobreescribir al objeto que estaba
        pacienteService.actualizarPaciente(pacienteAActualizar);

        // Ahora hago un get al objeto de id=1, que debería ser igual a pacienteAActualizar en vez del paciente original. Ahora le llamo pacienteActualizado
        Optional<PacienteDTO> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());

        // Verifico que pacienteActualizado tenga los nuevos datos. Ya no existe el paciente original
        assertEquals("Javi", pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(7)
    public void eliminarPacienteTest() {
        Long idAEliminar = 1L;

        // Elimino al paciente de id=1
        pacienteService.eliminarPaciente(idAEliminar);

        // Hago un get al paciente de id=1
        Optional<PacienteDTO> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);

        // Verifico que el get anterior no retornó nada
        assertFalse(pacienteEliminado.isPresent());
    }








//    @Test
//    @Order(1)
//    public void guardarPacienteTest() {
//        Paciente pacienteAGuardar = new Paciente();
//
//        pacienteAGuardar.setNombre("Andrés");
//        pacienteAGuardar.setApellido("Galván");
//        pacienteAGuardar.setDni("4900");
//        pacienteAGuardar.setEmail("8il.andre@gmail.com");
//        pacienteAGuardar.setFechaIngreso(LocalDate.of(1995, 5, 29));
//        pacienteAGuardar.setDomicilio(new Domicilio("Falsa", 123, "Sayago", "Montevideo"));
//
//        HashSet<Turno> turnoSetDePaciente = new HashSet<>();
//        Turno turnoDePaciente = new Turno(pacienteAGuardar, new Odontologo(), LocalDate.of(1850, 11, 30));
//        turnoSetDePaciente.add(turnoDePaciente);
//        pacienteAGuardar.setTurnoSet(turnoSetDePaciente);
//
//        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
//
//        assertEquals(1L, pacienteGuardado.getId());
//    }
//
//    @Test
//    @Order(2)
//    public void buscarPorIdTest() {
//        Long idABuscar = 1L;
//        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(idABuscar);
//
//        assertNotNull(pacienteBuscado);
//    }
//
//    @Test
//    @Order(3)
//    public void buscarPorNombreYApellidoTest() {
//        String nombre = "Andrés";
//        String apellido = "Galván";
//
//        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorNombreYApellido(nombre, apellido);
//
//        assertNotNull(pacienteBuscado);
//    }
//
//    @Test
//    @Order(4)
//    public void buscarPorEmailTest() {
//        String email = "8il.andre@gmail.com";
//
//        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
//
//        assertNotNull(pacienteBuscado);
//    }
//
//    @Test
//    @Order(5)
//    public void mostrarPacientesTest() {
//        List<Paciente> pacientes = pacienteService.mostrarPacientes();
//        Integer cantidadEsperada = 1;
//        assertEquals(cantidadEsperada, pacientes.size());
//    }
//
//    @Test
//    @Order(6)
//    public void actualizarPacienteTest() {
//        // Creo un nuevo paciente con los nuevos datos. Le hardcodeo el ID para que sea el mismo al paciente viejo así sea da cuenta que lo debe sobreescribir
//        Paciente pacienteAActualizar = new Paciente();
//        pacienteAActualizar.setId(1L);
//        pacienteAActualizar.setNombre("Javi");
//        pacienteAActualizar.setApellido("Grande");
//        pacienteAActualizar.setDni("999");
//        pacienteAActualizar.setEmail("grandeJavi@gmail.com");
//        pacienteAActualizar.setFechaIngreso(LocalDate.of(1999, 9, 29));
//        pacienteAActualizar.setDomicilio(new Domicilio("Corrientes", 4, "Ushuaia", "Argentina"));
//        pacienteAActualizar.setTurnoSet(new HashSet<>());
//
//        // Acá debería reconocer que el id=1 ya está en uso y sobreescribir al objeto que estaba
//        pacienteService.actualizarPaciente(pacienteAActualizar);
//
//        // Ahora hago un get al objeto de id=1, que debería ser igual a pacienteAActualizar en vez del paciente original. Ahora le llamo pacienteActualizado
//        Optional<Paciente> pacienteActualizado = pacienteService.buscarPorId(pacienteAActualizar.getId());
//
//        // Verifico que pacienteActualizado tenga los nuevos datos. Ya no existe el paciente original
//        assertEquals("Javi", pacienteActualizado.get().getNombre());
//    }
//
//    @Test
//    @Order(7)
//    public void eliminarPacienteTest() {
//        Long idAEliminar = 1L;
//
//        // Elimino al paciente de id=1
//        pacienteService.eliminarPaciente(idAEliminar);
//
//        // Hago un get al paciente de id=1
//        Optional<Paciente> pacienteEliminado = pacienteService.buscarPorId(idAEliminar);
//
//        // Verifico que el get anterior no retornó nada
//        assertFalse(pacienteEliminado.isPresent());
//    }

}