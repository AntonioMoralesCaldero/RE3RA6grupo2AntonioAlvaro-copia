package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.model.FamiliaProfesionalModel;

public interface FamiliaProfesionalService {

    List<FamiliaProfesional> obtenerTodasFamiliasProfesionales();

    FamiliaProfesional guardarFamiliaProfesional(FamiliaProfesional familiaProfesional);

    FamiliaProfesional obtenerFamiliaProfesionalPorId(int id);

    void borrarFamiliaProfesional(int id);
    FamiliaProfesional updateFamilia(FamiliaProfesionalModel familiaProfesionalModel);
    FamiliaProfesionalModel obtenerFamiliaProfesionalModelPorId(int id);
    
    FamiliaProfesional guardarFamiliaProfesional(FamiliaProfesionalModel familiaProfesionalModel);
    String obtenerNombrePorId(int id);
}
