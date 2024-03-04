package com.example.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Informe;
import com.example.demo.model.InformeModel;

@Component("informeConverter")
public class InformeConverter {
	public Informe transform(InformeModel informeModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(informeModel, Informe.class);
	}
	
	public InformeModel transform(Informe informe) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(informe, InformeModel.class);
	}

	
}

