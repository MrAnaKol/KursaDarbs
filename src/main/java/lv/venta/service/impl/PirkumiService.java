package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Pirkumi;
import lv.venta.repo.IDalibnieksRepo;
import lv.venta.repo.IPirkumiRepo;
import lv.venta.repo.IUzlabojumiRepo;
import lv.venta.service.IPirkumiService;

@Service
public class PirkumiService implements IPirkumiService{
	
	@Autowired
	private IPirkumiRepo pirkumiRepo;
	
	@Autowired
	private IDalibnieksRepo dalibnieksRepo;
	
	@Autowired
	private IUzlabojumiRepo uzlabojumiRepo;

	@Override
	public void izveidotPirkumaIerakstu(int idD, int idU) {
		Pirkumi jaunsPirkums = new Pirkumi(dalibnieksRepo.findByIdD(idD), uzlabojumiRepo.findByIdU(idU));
		pirkumiRepo.save(jaunsPirkums);
	}
	
}
