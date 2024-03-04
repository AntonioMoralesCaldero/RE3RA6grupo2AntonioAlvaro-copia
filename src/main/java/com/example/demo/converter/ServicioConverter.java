package com.example.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Servicio;
import com.example.demo.model.ServicioModel;

@Component("servicioConverter")
public class ServicioConverter {
	public Servicio transform(ServicioModel servicioModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(servicioModel, Servicio.class);
	}
	
	public ServicioModel transform(Servicio servicio) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(servicio, ServicioModel.class);
	}

	
}


