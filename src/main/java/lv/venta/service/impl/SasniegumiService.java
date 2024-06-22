package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.Sasniegumi;
import lv.venta.repo.ISasniegumiRepo;
import lv.venta.service.ISasniegumiService;

@Service
public class SasniegumiService implements ISasniegumiService{
	
	@Autowired
	private ISasniegumiRepo sasniegumiRepo;

	@Override
	public void izmainitPupinuSkaitu(int id, int skaits) throws Exception {
		Sasniegumi atjaunotiSasniegumi = izveletiesSasniegumusPecId(id);
		int pupinuKopejaisSkaits = atjaunotiSasniegumi.getPupinas().getPupinuKopejaisSkaits();
		int summa = pupinuKopejaisSkaits + skaits;
		if(summa < 0) {
			throw new Exception("Nav pietiekams pupiņu skaits, pietrūkst " + -summa);
		}
		else {
			atjaunotiSasniegumi.getPupinas().setPupinuKopejaisSkaits(summa);
		}
		sasniegumiRepo.save(atjaunotiSasniegumi);
	}

	@Override
	public void izmainitPupinuSkaituParKlikski(int id, int skaits) throws Exception {
		Sasniegumi atjaunotiSasniegumi = izveletiesSasniegumusPecId(id);
		int pupinuSkaitsParKlikski = atjaunotiSasniegumi.getPupinas().getPupinuSkaitsParKlikski();
		atjaunotiSasniegumi.getPupinas().setPupinuSkaitsParKlikski(pupinuSkaitsParKlikski + skaits);
		sasniegumiRepo.save(atjaunotiSasniegumi);
	}
	
	@Override
	public Sasniegumi izveletiesSasniegumusPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		
		Sasniegumi result = sasniegumiRepo.findByIdS(id);
		
		if(result == null)
			throw new Exception("Nav sasniegumu ar id " + id);
		
		return result;
	}	
	
}
