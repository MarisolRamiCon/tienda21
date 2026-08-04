package com.inndata21.tienda21.service.impl;
import com.inndata21.tienda21.service.IViajesService;
import com.inndata21.tienda21.repository.ViajesRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.inndata21.tienda21.dto.APITerceros.Viajes;
@Service
@Slf4j

public class ViajesService implements IViajesService {
    @Autowired
    ViajesRepository viajesRepository;

    @Override
    public List<Viajes> readAll() {
        log.info("estamos en el readAll de viajes");
        return viajesRepository.readAll();
    }

    @Override
    public Viajes readById(Integer id) {
        log.info("estamos en el readById de viajes");
        try{
            return viajesRepository.readById(id);
        } catch (Exception e) {
            log.error("Error al obtener el viaje con id " + id + ": " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Viajes create(Viajes viajes) {
        log.info("estamos en el create de viajes");
        try{
            return viajesRepository.create(viajes);
        } catch (Exception e) {
            log.error("Error al crear el viaje: " + e.getMessage());
            return null;
        }
    }

}
