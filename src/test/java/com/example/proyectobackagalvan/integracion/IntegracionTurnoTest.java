package com.example.proyectobackagalvan.integracion;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Domicilio;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import com.example.proyectobackagalvan.exception.BadRequestException;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;
import com.example.proyectobackagalvan.service.OdontologoService;
import com.example.proyectobackagalvan.service.PacienteService;
import com.example.proyectobackagalvan.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private MockMvc mockMvc;

    private void cargarTurnoinicial() throws ResourceNotFoundException, BadRequestException {
        Domicilio domicilio = new Domicilio("Calle a",548,"Salta Capital","Salta");
        Paciente pacienteAGuardar = new Paciente("Javi","Grande", "8888", "prueba@gmail.com", LocalDate.of(1950,11,30), domicilio);
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        Odontologo odontologoAGuardar = new Odontologo(12345, "Andrés", "Galván");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);

        TurnoDTO turnoDTOAGuardar = new TurnoDTO();
        turnoDTOAGuardar.setPacienteId(pacienteGuardado.getId());
        turnoDTOAGuardar.setOdontologoId(odontologoGuardado.getId());
        turnoDTOAGuardar.setFecha(LocalDate.of(2022,12,12));
        turnoService.guardarTurno(turnoDTOAGuardar);
    }

    @Test
    public void listadoTurnoTest() throws Exception {
        cargarTurnoinicial();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }
}
