package com.sofka.Springbootmongotest.Services;

import com.sofka.Springbootmongotest.Collections.Material;
import com.sofka.Springbootmongotest.DTOs.MaterialDTO;
import com.sofka.Springbootmongotest.Enums.AreaTematica;
import com.sofka.Springbootmongotest.Enums.TipoMaterial;
import com.sofka.Springbootmongotest.Repositories.MaterialRepositorio;
import com.sofka.Springbootmongotest.Utils.Filtro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MaterialServiceTest {

    @MockBean
    private MaterialRepositorio materialRepositorio;

    @Autowired
    private MaterialService materialService;

    @Test
    @DisplayName("TEST FINDALL")
    public void obtenerTodosTest(){
        var material = new Material("1234",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");
        var material2 = new Material("56789",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Ciencias,"Libro de la casa","muy buena");
        var lista=new ArrayList<Material>();
        lista.add(material);
        lista.add(material2);
        Mockito.when(materialRepositorio.findAll()).thenReturn(lista);

        var resultado = materialService.obtenerTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(material.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(material.getId(), resultado.get(0).getId());
        Assertions.assertEquals(material.getTipoMaterial(), resultado.get(0).getTipoMaterial());
        Assertions.assertEquals(material2.getNombre(), resultado.get(1).getNombre());
        Assertions.assertEquals(material2.getId(), resultado.get(1).getId());
        Assertions.assertEquals(material2.getTipoMaterial(), resultado.get(1).getTipoMaterial());

        Mockito.verify(materialRepositorio,Mockito.times(1)).findAll();

    }
    @Test
    @DisplayName("VALIDAR ESTADO")
    public void validarEstadoTest(){
        var material = new Material("1234",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");

        Mockito.when(materialRepositorio.findById("1234")).thenReturn(java.util.Optional.of(material));

        var resultado=materialService.validarEstado("1234");
        Assertions.assertEquals(material.isEstado(), resultado.isEstado());
        Assertions.assertEquals("El material esta disponible",resultado.getMensaje());

        Mockito.verify(materialRepositorio,Mockito.times(1)).findById("1234");
    }

    @Test
    @DisplayName("FILTRO TEST")
    public void filtroTest(){
        var material = new Material("1234",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");
        var material2 = new Material("56789",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la casa","muy buena");
        var lista=new ArrayList<Material>();
        lista.add(material);
        lista.add(material2);
        var filtro=new Filtro("Matematica","Libros");
        Mockito.when(materialRepositorio.findByAreaTematicaAndTipoMaterial(filtro.getArea(),filtro.getTipo())).thenReturn(lista);

        var resultado = materialService.filtros(filtro);

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(material.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(material.getId(), resultado.get(0).getId());
        Assertions.assertEquals(filtro.getTipo(), resultado.get(0).getTipoMaterial().toString());
        Assertions.assertEquals(material2.getNombre(), resultado.get(1).getNombre());
        Assertions.assertEquals(material2.getId(), resultado.get(1).getId());
        Assertions.assertEquals(filtro.getTipo(), resultado.get(1).getTipoMaterial().toString());

        Mockito.verify(materialRepositorio,Mockito.times(1)).findByAreaTematicaAndTipoMaterial(filtro.getArea(),filtro.getTipo());

    }

    @Test
    @DisplayName("Prestar Libro test")
    public void prestarUnMaterialTest(){
        var material = new Material("1234",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");
        var material2 = new Material("1234",false, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");

        Mockito.when(materialRepositorio.findById("1234")).thenReturn(java.util.Optional.of(material));
        Mockito.when(materialRepositorio.save(any())).thenReturn(material2);

        var resultado = materialService.prestarUnMaterial("1234");

        Assertions.assertEquals("Transaccion satisfactoriamente", resultado.getMensaje());

        Mockito.verify(materialRepositorio,Mockito.times(1)).findById("1234");

    }

    @Test
    @DisplayName("devolver Libro test")
    public void devolverUnMaterialTest(){
        var material = new Material("1234",false, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");
        var material2 = new Material("1234",true, LocalDate.now(), TipoMaterial.Libros, AreaTematica.Matematica,"Libro de la vida","muy buena");

        Mockito.when(materialRepositorio.findById("1234")).thenReturn(java.util.Optional.of(material));
        Mockito.when(materialRepositorio.save(any())).thenReturn(material2);

        var resultado = materialService.devolverUnMaterial("1234");

        Assertions.assertEquals("Transaccion satisfactoriamente", resultado.getMensaje());
    }

}