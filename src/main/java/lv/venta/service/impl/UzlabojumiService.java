package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.UzlabojumaTips;
import lv.venta.model.Uzlabojumi;
import lv.venta.repo.IUzlabojumiRepo;
import lv.venta.service.IKoksService;
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

	@Override
	public void izdzestUzlabojumuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		uzlabojumiRepo.deleteByIdU(id);
	}

	@Override
	public void nopirktUzlabojumu(int idU, int idD, int cena, UzlabojumaTips uzlabojumaTips) throws Exception {
		if(idU < 0 || idD < 0) throw new Exception("Id nevar būt negatīvs");
		Uzlabojumi uzlabojums = izveletiesUzlabojumuPecId(idU);
		switch (uzlabojums.getUzlabojumaTips()) {
		case atrums:
			sasniegumiService.izmainitPupinuSkaitu(idD, cena);
			sasniegumiService.izmainitPupinuSkaituParKlikski(idD, 1);
			break;
		case udens:
			sasniegumiService.izmainitPupinuSkaitu(idD, cena);
			kokaService.izmainitKokaAugstumu(idD, 5);
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
