//package com.example.proyectobackagalvan.service;
//
//import com.example.proyectobackagalvan.dto.OdontologoDTO;
//import com.example.proyectobackagalvan.dto.TurnoDTO;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class OdontologoServiceTest {
//
//    @Autowired
//    IOdontologoService odontologoService;
//
//    @Test
//    @Order(1)
//    public void guardarOdontologoTest() {
//        OdontologoDTO odontologoAGuardar = new OdontologoDTO();
//
//        odontologoAGuardar.setMatricula(1999);
//        odontologoAGuardar.setNombre("Andrés");
//        odontologoAGuardar.setApellido("Galván");
//
//        List<Long> turnoSetDeOdontologo = new ArrayList<>();
//        TurnoDTO turnoDeOdontologo = new TurnoDTO();
//        turnoSetDeOdontologo.add(turnoDeOdontologo.getOdontologoId());
//        odontologoAGuardar.setTurnoIdList(turnoSetDeOdontologo);
//
//        OdontologoDTO odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
//
//        assertEquals(1L, odontologoGuardado.getId());
//    }
//
//    @Test
//    @Order(2)
//    public void buscarPorIdTest() {
//        Long idABuscar = 1L;
//        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);
//
//        assertNotNull(odontologoBuscado);
//    }
//
//    @Test
//    @Order(3)
//    public void buscarPorNombreYApellidoTest() {
//        String nombre = "Andrés";
//        String apellido = "Galván";
//
//        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarPorNombreYApellido(nombre, apellido);
//
//        assertNotNull(odontologoBuscado);
//    }
//
//    @Test
//    @Order(4)
//    public void buscarPorMatriculaTest() {
//        Integer matricula = 1234;
//
//        Optional<OdontologoDTO> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
//
//        assertNotNull(odontologoBuscado);
//    }
//
//    @Test
//    @Order(5)
//    public void mostrarOdontologosTest() {
//        List<OdontologoDTO> odontologos = odontologoService.mostrarOdontologos();
//        Integer cantidadEsperada = 1;
//
//        assertEquals(cantidadEsperada, odontologos.size());
//    }
//
//    @Test
//    @Order(6)
//    public void actualizarOdontologoTest() {
//        OdontologoDTO odontologoAActualizar = new OdontologoDTO();
//        odontologoAActualizar.setId(1L);
//        odontologoAActualizar.setMatricula(999);
//        odontologoAActualizar.setNombre("Javi");
//        odontologoAActualizar.setApellido("Grande");
//        odontologoAActualizar.setTurnoIdList(new ArrayList<>());
//
//        odontologoService.actualizarOdontologo(odontologoAActualizar);
//
//        Optional<OdontologoDTO> odontologoActualizado = odontologoService.buscarOdontologo(odontologoAActualizar.getId());
//
//        assertEquals("Javi", odontologoActualizado.get().getNombre());
//    }
//
//    @Test
//    @Order(7)
//    public void eliminarOdontologoTest() {
//        Long idAEliminar = 1L;
//
//        odontologoService.eliminarOdontologo(idAEliminar);
//
//        Optional<OdontologoDTO> odontologoEliminado = odontologoService.buscarOdontologo(idAEliminar);
//
//        assertFalse(odontologoEliminado.isPresent());
//    }
//
//}