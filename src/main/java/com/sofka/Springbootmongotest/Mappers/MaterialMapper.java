package com.sofka.Springbootmongotest.Mappers;

import com.sofka.Springbootmongotest.Collections.Material;
import com.sofka.Springbootmongotest.DTOs.MaterialDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaterialMapper {
    public Material fromDTO(MaterialDTO dto) {
        Material material = new Material();
        material.setId(dto.getId());
        material.setNombre(dto.getNombre());
        material.setEstado(dto.isEstado());
        material.setFechaPrestamos(dto.getFechaPrestamos());
        material.setTipoMaterial(dto.getTipoMaterial());
        material.setAreaTematica(dto.getAreaTematica());
        material.setDescripcion(dto.getDescripcion());
        return material;
    }

    public MaterialDTO fromCollection(Material collection) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(collection.getId());
        materialDTO.setNombre(collection.getNombre());
        materialDTO.setEstado(collection.isEstado());
        materialDTO.setFechaPrestamos(collection.getFechaPrestamos());
        materialDTO.setTipoMaterial(collection.getTipoMaterial());
        materialDTO.setAreaTematica(collection.getAreaTematica());
        materialDTO.setDescripcion(collection.getDescripcion());
        return materialDTO;
    }

    public List<MaterialDTO> fromCollectionList(List<Material> collection) {
        if (collection == null) {
            return null;

        }
        List<MaterialDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Material material = (Material) listTracks.next();
            list.add(fromCollection(material));
        }

        return list;
    }
}
