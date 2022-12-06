package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarOdontologo(Long id);
    Optional<Odontologo> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Odontologo> buscarPorMatricula(Integer matricula);
    List<Odontologo> mostrarOdontologos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
}