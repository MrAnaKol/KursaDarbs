package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.repo.IDalibnieksRepo;
import lv.venta.service.IDalibnieksCRUDService;

@Service
public class DalibnieksCRUDService implements IDalibnieksCRUDService {

	@Autowired
	private IDalibnieksRepo dalibnieksRepo;
}
