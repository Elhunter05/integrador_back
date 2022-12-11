package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarOdontologo(Long id) throws ResourceNotFoundException;
    Optional<Odontologo> buscarPorMatricula(Integer matricula) throws ResourceNotFoundException;
    List<Odontologo> buscarOdontologosPorNombreYApellido(String nombre, String apellido);
    List<Odontologo> mostrarOdontologos();
    void actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}