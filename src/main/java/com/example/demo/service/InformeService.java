package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Informe;
import com.example.demo.model.InformeModel;

public interface InformeService {
	List<InformeModel> ListAllInforme();
	Informe addInforme(InformeModel informeModel);
	int removeInforme(int id);
	Informe updateInforme(InformeModel informeModel);
}
