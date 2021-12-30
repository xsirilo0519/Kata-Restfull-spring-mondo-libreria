package com.sofka.Springbootmongotest.Controllers;


import com.sofka.Springbootmongotest.DTOs.MaterialDTO;
import com.sofka.Springbootmongotest.Enums.AreaTematica;
import com.sofka.Springbootmongotest.Enums.TipoMaterial;
import com.sofka.Springbootmongotest.Services.MaterialService;
import com.sofka.Springbootmongotest.Utils.Filtro;
import com.sofka.Springbootmongotest.Utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class MaterialController {
    @Autowired
    MaterialService materialService;
    //listo
    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(materialService.obtenerPorId(id), HttpStatus.OK);
    }

    //listo
    @PutMapping("/modificar")
    public ResponseEntity<MaterialDTO> update(@RequestBody MaterialDTO materialDTO) {
        if (materialDTO.getId() != null) {
            return new ResponseEntity(materialService.modificar(materialDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    //listo
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            materialService.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //listo
    @GetMapping()
    public ResponseEntity<List<MaterialDTO>> findAll() {
        return new ResponseEntity(materialService.obtenerTodos(), HttpStatus.OK);
    }
    //listo
    @PostMapping("/crear")
    public ResponseEntity<MaterialDTO> create(@RequestBody MaterialDTO empleadoDTO) {
        return new ResponseEntity(materialService.crear(empleadoDTO), HttpStatus.CREATED);
    }

    //-------
    //listo
    @GetMapping("/filtrar")
    public ResponseEntity<List<MaterialDTO>> findByFiltro(@RequestBody Filtro filtro) {
        return new ResponseEntity(materialService.filtros(filtro), HttpStatus.OK);
    }
    //listo
    @GetMapping("/validar/{id}")
    public ResponseEntity<Mensaje> validarEstado(@PathVariable("id") String id) {
        return new ResponseEntity(materialService.validarEstado(id), HttpStatus.OK);
    }
    //listo
    @GetMapping("/prestar/{id}")
    public ResponseEntity<Mensaje> prestarMaterial(@PathVariable("id") String id) {
        return new ResponseEntity(materialService.prestarUnMaterial(id), HttpStatus.OK);
    }
    //listo
    @GetMapping("/devolver/{id}")
    public ResponseEntity<Mensaje> devolverMaterial(@PathVariable("id") String id) {
        return new ResponseEntity(materialService.devolverUnMaterial(id), HttpStatus.OK);
    }

}
