package com.sofka.Springbootmongotest.Controllers;

import com.sofka.Springbootmongotest.Services.MaterialService;
import com.sofka.Springbootmongotest.Utils.Mensaje;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MaterialControllerTest {

    @MockBean
    private MaterialService materialService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("validar estado")
    public void validarEstadoTest() throws Exception {
        var mensaje=new Mensaje();
        mensaje.setEstado(true);
        mensaje.setMensaje("El material esta disponible");
        Mockito.when(materialService.validarEstado("1234")).thenReturn(mensaje);

        mockMvc.perform(get("/biblioteca/validar/{id}", "1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("El material esta disponible")));


        Mockito.verify(materialService,Mockito.times(1)).validarEstado("1234");
    }

    @Test
    @DisplayName("prestar")
    public void prestarTest() throws Exception {
        var mensaje=new Mensaje();
        mensaje.setEstado(true);
        mensaje.setMensaje("El material esta disponible");
        Mockito.when(materialService.prestarUnMaterial("1234")).thenReturn(mensaje);

        mockMvc.perform(get("/biblioteca/prestar/{id}", "1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("El material esta disponible")));


        Mockito.verify(materialService,Mockito.times(1)).prestarUnMaterial("1234");
    }

    @Test
    @DisplayName("devolver")
    public void devolverTest() throws Exception {
        var mensaje=new Mensaje();
        mensaje.setEstado(true);
        mensaje.setMensaje("El material esta disponible");
        Mockito.when(materialService.prestarUnMaterial("1234")).thenReturn(mensaje);

        mockMvc.perform(get("/biblioteca/prestar/{id}", "1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("El material esta disponible")));


        Mockito.verify(materialService,Mockito.times(1)).prestarUnMaterial("1234");
    }

}