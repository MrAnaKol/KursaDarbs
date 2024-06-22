package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Koks;
import lv.venta.model.Sasniegumi;
import lv.venta.repo.IKoksRepo;
import lv.venta.repo.ISasniegumiRepo;
import lv.venta.repo.IUzlabojumiRepo;
import lv.venta.service.IKoksService;

@Service
public class KoksService implements IKoksService{
	
	@Autowired
	private IKoksRepo kokaRepo;
	
	@Autowired
	private IUzlabojumiRepo uzlabojumiRepo;
	
	@Autowired
	private ISasniegumiRepo sasniegumiRepo;

	@Override
	public void izdzestKokuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		kokaRepo.deleteByIdK(id);
	}

	@Override
	public Koks izveletiesKokuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		
		Koks result = kokaRepo.findByIdK(id);
		
		if(result == null)
			throw new Exception("Nav koka ar id " + id);
		
		return result;
	}

	@Override
	public void izmainitKokaAugstumu(int id, int skaits) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		Koks atjaunotsKoks = izveletiesKokuPecId(id);
		Sasniegumi atjaunotiSasniegumi = sasniegumiRepo.findByIdS(id);
		int augstums = atjaunotsKoks.getAugstums();
		atjaunotsKoks.setAugstums(augstums + skaits);
		
		switch (atjaunotsKoks.getAugstums()) {
		case 10:
			atjaunotsKoks.setKokaLimenis(1);
			atjaunotiSasniegumi.setKokaLimenis(1);
			break;
		case 100:
			atjaunotsKoks.setKokaLimenis(2);
			atjaunotiSasniegumi.setKokaLimenis(2);
			break;
		case 1000:
			atjaunotsKoks.setKokaLimenis(3);
			atjaunotiSasniegumi.setKokaLimenis(3);
			break;
		case 10000:
			atjaunotsKoks.setKokaLimenis(4);
			atjaunotiSasniegumi.setKokaLimenis(4);
			break;
		}
		
		kokaRepo.save(atjaunotsKoks);
		atjaunotiSasniegumi.setKoks(atjaunotsKoks);
		sasniegumiRepo.save(atjaunotiSasniegumi);
	}

	@Override
	public void pieliktUzlabojumu(int id, int idU) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		Koks atjaunotsKoks = izveletiesKokuPecId(id);
		atjaunotsKoks.addUzlabojumi(uzlabojumiRepo.findByIdU(idU));
		kokaRepo.save(atjaunotsKoks);
	}
	
}
