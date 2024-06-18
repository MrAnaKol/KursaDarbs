package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.repo.IPupinasRepo;
import lv.venta.service.IPupinasService;

@Service
public class PupinasService implements IPupinasService {

	@Autowired
	private IPupinasRepo pupinasRepo;
}
