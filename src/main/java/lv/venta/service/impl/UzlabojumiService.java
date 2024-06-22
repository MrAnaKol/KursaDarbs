package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.UzlabojumaTips;
import lv.venta.model.Uzlabojumi;
import lv.venta.repo.IUzlabojumiRepo;
import lv.venta.service.IKoksService;
import lv.venta.service.IPirkumiService;
import lv.venta.service.ISasniegumiService;
import lv.venta.service.IUzlabojumiService;

@Service
public class UzlabojumiService implements IUzlabojumiService{
	
	@Autowired
	private IUzlabojumiRepo uzlabojumiRepo;
	
	@Autowired
	private ISasniegumiService sasniegumiService;
	
	@Autowired
	private IKoksService kokaService;
	
	@Autowired
	private IPirkumiService pirkumuService;

	@Override
	public void izdzestUzlabojumuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		uzlabojumiRepo.deleteByIdU(id);
	}

	@Override
	public void nopirktUzlabojumu(int idU, int idD, int cena) throws Exception {
		if(idU < 0 || idD < 0) throw new Exception("Id nevar būt negatīvs");
		Uzlabojumi uzlabojums = izveletiesUzlabojumuPecId(idU);
		switch (uzlabojums.getUzlabojumaTips()) {
		case atrums:
			sasniegumiService.izmainitPupinuSkaitu(idD, -cena);
			pirkumuService.izveidotPirkumaIerakstu(idD, idU);
			sasniegumiService.izmainitPupinuSkaituParKlikski(idD, 1);
			kokaService.pieliktUzlabojumu(idD, idU);
			break;
		case udens:
			sasniegumiService.izmainitPupinuSkaitu(idD, -cena);
			pirkumuService.izveidotPirkumaIerakstu(idD, idU);
			kokaService.izmainitKokaAugstumu(idD, 5);
			kokaService.pieliktUzlabojumu(idD, idU);
			break;
		}
	}

	@Override
	public Uzlabojumi izveletiesUzlabojumuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		
		Uzlabojumi result = uzlabojumiRepo.findByIdU(id);
		
		if(result == null)
			throw new Exception("Nav uzlabojumu ar id " + id);
		
		return result;
	}

	@Override
	public void jaunsUzlabojums(String nosaukums, int cena, UzlabojumaTips uzlabojumaTips) throws Exception {
		if(cena < 0) throw new Exception("Cena nevar būt negatīva");
		Uzlabojumi jaunsUzlabojums = new Uzlabojumi(nosaukums, cena, uzlabojumaTips);
		uzlabojumiRepo.save(jaunsUzlabojums);
	}
	
}
