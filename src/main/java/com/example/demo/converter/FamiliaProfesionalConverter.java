package com.example.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.model.FamiliaProfesionalModel;

@Component("familiaProfesionalConverter")
public class FamiliaProfesionalConverter {
	public FamiliaProfesional transform(FamiliaProfesionalModel familiaProfesionalModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(familiaProfesionalModel, FamiliaProfesional.class);
	}
	
	public FamiliaProfesionalModel transform(FamiliaProfesional familiaProfesional) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(familiaProfesional, FamiliaProfesionalModel.class);
	}

	
}

