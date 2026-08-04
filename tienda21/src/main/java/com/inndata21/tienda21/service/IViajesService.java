package com.inndata21.tienda21.service;
import java.util.List;

import com.inndata21.tienda21.dto.APITerceros.Viajes;


public interface IViajesService {
    public List<Viajes> readAll();
    public Viajes readById(Integer id);
    public Viajes create(Viajes viajes);
}
