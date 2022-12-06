package com.example.proyectobackagalvan.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
