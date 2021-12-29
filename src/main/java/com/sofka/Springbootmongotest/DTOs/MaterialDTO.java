package com.sofka.Springbootmongotest.DTOs;

import com.sofka.Springbootmongotest.Enums.AreaTematica;
import com.sofka.Springbootmongotest.Enums.TipoMaterial;

import java.time.LocalDate;

public class MaterialDTO {
    private String id;
    private boolean estado;
    private LocalDate fechaPrestamos;
    private TipoMaterial tipoMaterial;
    private AreaTematica areaTematica;
    private String nombre;
    private String descripcion;

    public MaterialDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPrestamos() {
        return fechaPrestamos;
    }

    public void setFechaPrestamos(LocalDate fechaPrestamos) {
        this.fechaPrestamos = fechaPrestamos;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
