package com.sofka.Springbootmongotest.Services;

import com.sofka.Springbootmongotest.Collections.Material;
import com.sofka.Springbootmongotest.DTOs.MaterialDTO;
import com.sofka.Springbootmongotest.Mappers.MaterialMapper;
import com.sofka.Springbootmongotest.Repositories.MaterialRepositorio;
import com.sofka.Springbootmongotest.Utils.Filtro;
import com.sofka.Springbootmongotest.Utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialService {

    private final String MENSAGE_PRESTADO="El material se encuentra prestado";
    private final String MENSAGE_DISPONIBLE="El material esta disponible";


    @Autowired
    private MaterialRepositorio materialRepositorio;

    MaterialMapper mapper = new MaterialMapper();

    public List<MaterialDTO> obtenerTodos() {
        List<Material> materials = materialRepositorio.findAll();
        return mapper.fromCollectionList(materials);
    }

    public MaterialDTO obtenerPorId(String id){
        Material material= materialRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Material no encontrado"));
        return mapper.fromCollection(material);
    }

    public MaterialDTO crear(MaterialDTO materialDTO) {
        Material material = mapper.fromDTO(materialDTO);
        return mapper.fromCollection(materialRepositorio.save(material));
    }

    public MaterialDTO modificar(MaterialDTO materialDTO) {
        Material material = mapper.fromDTO(materialDTO);
        materialRepositorio.findById(material.getId()).orElseThrow(() -> new RuntimeException("Material no encontrado"));
        return mapper.fromCollection(materialRepositorio.save(material));
    }

    public void borrar(String id) {
        materialRepositorio.deleteById(id);
    }


    //obtener si esta disponible o no
    public Mensaje validarEstado(String id){
        MaterialDTO materialDTO=obtenerPorId(id);
        boolean disponible=materialDTO.isEstado();
        return getMensajeValidado(materialDTO, disponible);
    }


    //Prestar un material
     public Mensaje prestarUnMaterial(String id){
         MaterialDTO materialDTO=obtenerPorId(id);
         boolean disponible=materialDTO.isEstado();
         return getMensajeCambioEstado(materialDTO, disponible,MENSAGE_PRESTADO);
     }

     //Devolver un material
     public Mensaje devolverUnMaterial(String id){
         MaterialDTO materialDTO=obtenerPorId(id);
         boolean disponible=materialDTO.isEstado();
         return getMensajeCambioEstado(materialDTO, !disponible,MENSAGE_DISPONIBLE);
     }


    private void cambiarEstado(MaterialDTO materialDTO) {
        materialDTO.setEstado(!materialDTO.isEstado());
        if(!materialDTO.isEstado()){
            materialDTO.setFechaPrestamos(LocalDate.now());
        }
        Material material = mapper.fromDTO(materialDTO);
        materialRepositorio.save(material);
    }


    private List<MaterialDTO> obtenerPorareayTipo(String areaTematica,String tipoMaterial){
        List<Material> materials = materialRepositorio.findByAreaTematicaAndTipoMaterial(areaTematica,tipoMaterial);
        return mapper.fromCollectionList(materials);
    }
    private List<MaterialDTO> obtenerPorareaoTipo(String areaTematica,String tipoMaterial){
        List<Material> materials = materialRepositorio.findByAreaTematicaOrTipoMaterial(areaTematica,tipoMaterial);
        return mapper.fromCollectionList(materials);
    }

    public List<MaterialDTO> filtros(Filtro filtro) {
        String tipoMaterial=filtro.getTipo();
        String areaTematica=filtro.getArea();
        List<MaterialDTO> materialDTOS;
        if (busquedaDoble(tipoMaterial, areaTematica)){
            materialDTOS=obtenerPorareayTipo(areaTematica,tipoMaterial);
        }else{
            materialDTOS=obtenerPorareaoTipo(areaTematica,tipoMaterial);
        }
        return materialDTOS;

    }

    private boolean esVacio(String object) {
        return object!=null&&object!="";
    }

    private boolean busquedaDoble(String tipoMaterial, String areaTematica) {
        return esVacio(tipoMaterial)&& esVacio(areaTematica);
    }

    private Mensaje getMensajeValidado(MaterialDTO materialDTO, boolean disponible) {
        Mensaje mensaje=new Mensaje();
        mensaje.setEstado(disponible);
        if(disponible){
            mensaje.setMensaje(MENSAGE_DISPONIBLE);
        }else{
            mensaje.setMensaje("El material fue prestado el: "+ materialDTO.getFechaPrestamos());
        }
        return mensaje;
    }

    private Mensaje getMensajeCambioEstado(MaterialDTO materialDTO, boolean disponible,String mensaje) {
        if(disponible){
            cambiarEstado(materialDTO);
            return new Mensaje(disponible,"Transaccion satisfactoriamente");
        }
        return new Mensaje(disponible,mensaje );
    }
}
