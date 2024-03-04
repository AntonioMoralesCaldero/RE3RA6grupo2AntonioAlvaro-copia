package com.example.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Alumno;
import com.example.demo.model.AlumnoModel;

@Component("alumnoConverter")
public class AlumnoConverter {
	public Alumno transform(AlumnoModel alumnoModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(alumnoModel, Alumno.class);
	}
	
	public AlumnoModel transform(Alumno alumno) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(alumno, AlumnoModel.class);
	}

	
}
