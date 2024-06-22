package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.Dalibnieks;
import lv.venta.repo.IDalibnieksRepo;
import lv.venta.service.IDalibnieksCRUDService;

@Service
public class DalibnieksCRUDService implements IDalibnieksCRUDService {

	@Autowired
	private IDalibnieksRepo dalibnieksRepo;

	@Override
	public void izdzestDalibniekuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		dalibnieksRepo.deleteByIdD(id);
	}

	@Override
	public void jaunsDalibnieks(String loma, String lietotajvards, String parole) throws Exception {
		Dalibnieks dalibnieks = dalibnieksRepo.findByLietotajvards(lietotajvards);
		if(dalibnieks != null){
			throw new Exception("Tāds dalībnieks jau eksistē");
		}
		Dalibnieks jaunsDalibnieks = new Dalibnieks(loma, lietotajvards, parole);
		dalibnieksRepo.save(jaunsDalibnieks);
	}

	@Override
	public void atjaunotDalibniekuPecId(int id, String loma, String lietotajvards, String parole) throws Exception {
		Dalibnieks atjaunotsDalibnieks = izveletiesDalibniekuPecId(id);
		atjaunotsDalibnieks.setLoma(loma);
		atjaunotsDalibnieks.setLietotajvards(lietotajvards);
		atjaunotsDalibnieks.setParole(parole);
		dalibnieksRepo.save(atjaunotsDalibnieks);
	}

	@Override
	public Dalibnieks izveletiesDalibniekuPecId(int id) throws Exception {
		if(id < 0) throw new Exception("Id nevar būt negatīvs");
		
		Dalibnieks result = dalibnieksRepo.findByIdD(id);
		
		if(result == null)
			throw new Exception("Nav dalībnieka ar id " + id);
		
		return result;
	}
}
