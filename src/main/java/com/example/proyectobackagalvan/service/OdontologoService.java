package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {
    private final OdontologoRepository odontologoRepository;
    private final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        LOGGER.info("Se ha registrado exitosamente un nuevo odontólogo");
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarOdontologo(Long id) {
        LOGGER.info("Iniciando la búsqueda de un odontólogo con id="+id);
        return odontologoRepository.findById(id);
    }
    public Optional<Odontologo> buscarPorNombreYApellido(String nombre, String apellido) {
        LOGGER.info("Iniciando la búsqueda de un odontólogo con nombre="+nombre+" y apellido="+apellido);
        return odontologoRepository.findByNombreAndApellido(nombre, apellido);
    }
    public Optional<Odontologo> buscarPorMatricula(Integer matricula) {
        LOGGER.info("Iniciando la búsqueda de un odontólogo con matrícula="+matricula);
        return odontologoRepository.findByMatricula(matricula);
    }
    public List<Odontologo> mostrarOdontologos() {
        LOGGER.info("Iniciando la búsqueda de todos los odontólogos");
        return odontologoRepository.findAll();
    }
    public void actualizarOdontologo(Odontologo odontologo) {
        LOGGER.info("Iniciando la actualización del odontólogo con id="+odontologo.getId());
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id) {
        LOGGER.info("Iniciando la eliminación del odontólogo con id="+id);
        odontologoRepository.deleteById(id);
    }
}
