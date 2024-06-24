package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Koks;
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
	public void nopirktUzlabojumu(int idU, int idD) throws Exception {
		if(idU < 0 || idD < 0) throw new Exception("Id nevar būt negatīvs");
		Uzlabojumi uzlabojums = izveletiesUzlabojumuPecId(idU);
		int cena = uzlabojums.getCena();
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
			kokaService.izmainitKokaAugstumu(idD, 10);
			kokaService.pieliktUzlabojumu(idD, idU);
			break;
		case autonoms:
			sasniegumiService.izmainitPupinuSkaitu(idD, -cena);
			pirkumuService.izveidotPirkumaIerakstu(idD, idU);
			sasniegumiService.izmainitAutonomuPupinuSkaitu(idD, 1);
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

	@Override
	public ArrayList<Uzlabojumi> izveletiesVisusNeizmantotusUzlabojumus(int id) throws Exception {
		ArrayList<Uzlabojumi> result = new ArrayList<>();
		ArrayList<Uzlabojumi> galaResult = new ArrayList<>();
		Iterable<Uzlabojumi> uzlabojumi = uzlabojumiRepo.findAll();
		Koks koks = kokaService.izveletiesKokuPecId(id);
		Collection<Uzlabojumi> kokaUzlabojumi = koks.getUzlabojumi();
		for(Uzlabojumi uzlabojums : uzlabojumi) {
			for(Uzlabojumi kokaUzlabojums : kokaUzlabojumi) {
				if(kokaUzlabojums.equals(uzlabojums)) {
					result.add(uzlabojums);
				}
			}

		}
		for(Uzlabojumi uzlabojums : uzlabojumi) {
			galaResult.add(uzlabojums);
		}
		for(Uzlabojumi uzlabojums : result) {
			galaResult.remove(uzlabojums);
		}
		return galaResult;
	}

	@Override
	public Uzlabojumi izveletiesUzlabojumuPecNosaukumaUnCenas(String nosaukums, int cena) throws Exception {
		Uzlabojumi uzlabojums = uzlabojumiRepo.findByNosaukumsAndCena(nosaukums, cena);
		return uzlabojums;
	}
	
}
