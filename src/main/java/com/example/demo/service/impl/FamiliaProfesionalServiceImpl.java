package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.model.FamiliaProfesionalModel;
import com.example.demo.repository.FamiliaProfesionalRepository;
import com.example.demo.service.FamiliaProfesionalService;

@Service
public class FamiliaProfesionalServiceImpl implements FamiliaProfesionalService {

    @Autowired
    private FamiliaProfesionalRepository familiaProfesionalRepository;

    @Override
    public List<FamiliaProfesional> obtenerTodasFamiliasProfesionales() {
        return familiaProfesionalRepository.findAll();
    }

    @Override
    public FamiliaProfesional guardarFamiliaProfesional(FamiliaProfesional familiaProfesional) {
        return familiaProfesionalRepository.save(familiaProfesional);
    }

    @Override
    public FamiliaProfesional obtenerFamiliaProfesionalPorId(int id) {
        Optional<FamiliaProfesional> optionalFamilia = familiaProfesionalRepository.findById(id);
        return optionalFamilia.orElse(null);
    }

    @Override
    public void borrarFamiliaProfesional(int id) {
        Optional<FamiliaProfesional> optionalFamilia = familiaProfesionalRepository.findById(id);
        optionalFamilia.ifPresent(familia -> {
            List<Alumno> alumnosCopy = new ArrayList<>(familia.getAlumnoList());

            for (Alumno alumno : alumnosCopy) {
                alumno.setFamiliaProfesional(null);
            }

            familiaProfesionalRepository.delete(familia);
        });
    }
 

    @Override
    public FamiliaProfesional guardarFamiliaProfesional(FamiliaProfesionalModel familiaProfesionalModel) {
        FamiliaProfesional familiaProfesional = convertToFamilia(familiaProfesionalModel);
        return guardarFamiliaProfesional(familiaProfesional);
    }

    @Override
    public FamiliaProfesional updateFamilia(FamiliaProfesionalModel familiaProfesionalModel) {
        FamiliaProfesional familia = familiaProfesionalRepository.findById(familiaProfesionalModel.getId())
                .orElseThrow(() -> new RuntimeException("Familia no encontrada"));

        familia.setNombre(familiaProfesionalModel.getNombre());

        return familiaProfesionalRepository.save(familia);
    }

    private FamiliaProfesional convertToFamilia(FamiliaProfesionalModel familiaProfesionalModel) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(familiaProfesionalModel, FamiliaProfesional.class);
    }

    private FamiliaProfesionalModel convertToFamiliaModel(FamiliaProfesional familiaProfesional) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(familiaProfesional, FamiliaProfesionalModel.class);
    }
    
    @Override
    public FamiliaProfesionalModel obtenerFamiliaProfesionalModelPorId(int id) {
        Optional<FamiliaProfesional> optionalFamilia = familiaProfesionalRepository.findById(id);

        if (optionalFamilia.isPresent()) {
            FamiliaProfesional familia = optionalFamilia.get();
            return convertToFamiliaModel(familia);
        }

        throw new IllegalArgumentException("Familia no encontrada con ID: " + id);
    }

    @Override
    public String obtenerNombrePorId(int id) {
        FamiliaProfesional familiaProfesional = familiaProfesionalRepository.findById(id).orElse(null);
        if (familiaProfesional != null) {
            return familiaProfesional.getNombre();
        }
        return "";
    }


}
