package com.sofka.Springbootmongotest.Repositories;

import com.sofka.Springbootmongotest.Collections.Material;
import com.sofka.Springbootmongotest.Enums.AreaTematica;
import com.sofka.Springbootmongotest.Enums.TipoMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepositorio extends MongoRepository<Material, String> {
    List<Material> findByAreaTematicaOrTipoMaterial(String areaTematica,String tipoMaterial);
    List<Material> findByAreaTematicaAndTipoMaterial(String areaTematica,String tipoMaterial);

}

