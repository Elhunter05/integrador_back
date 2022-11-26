package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) { this.odontologoRepository = odontologoRepository; }
    public Odontologo registrarOdontologo(Odontologo odontologo) { return odontologoRepository.save(odontologo); }
    public Optional<Odontologo> buscarOdontologo(Long id) { return odontologoRepository.findById(id); }
    public Optional<Odontologo> buscarOdontologoPorNombreCompleto(String nombre, String apellido) { return odontologoRepository.findByNombreAndApellido(nombre, apellido); }
    public Optional<Odontologo> buscarOdontologoPorMatricula(Integer matricula) { return odontologoRepository.findByMatricula(matricula); }
    public List<Odontologo> listarOdontologos() { return odontologoRepository.findAll(); }
    public void actualizarOdontologo(Odontologo odontologo) { odontologoRepository.save(odontologo); }
    public void eliminarOdontologo(Long id) { odontologoRepository.deleteById(id); }

}
