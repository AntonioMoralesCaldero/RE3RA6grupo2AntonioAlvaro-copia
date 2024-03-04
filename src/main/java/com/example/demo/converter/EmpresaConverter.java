package com.example.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Empresa;
import com.example.demo.model.EmpresaModel;

@Component("empresaConverter")
public class EmpresaConverter {
	public Empresa transform(EmpresaModel empresaModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(empresaModel, Empresa.class);
	}
	
	public EmpresaModel transform(Empresa empresa) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(empresa, EmpresaModel.class);
	}

	
}
