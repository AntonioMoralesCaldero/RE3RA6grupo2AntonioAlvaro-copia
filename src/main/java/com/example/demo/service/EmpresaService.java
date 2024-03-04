package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Empresa;
import com.example.demo.model.EmpresaModel;

@Service
public interface EmpresaService {
	 List<Empresa> ListAllEmpresas();
	
	 Empresa ListAllEmpresasById(int id);
	 Empresa updateEmpresa(Empresa empresa);
	 Empresa guardarEmpresa(Empresa empresa);
	 EmpresaModel getEmpresaById(int id);
	 Empresa updateEmpresa(EmpresaModel empresaModel);
	 Empresa findById(int id);
	 int deleteEmpresa(int id);
	 Empresa findByEmail();

}
