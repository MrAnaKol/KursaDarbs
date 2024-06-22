package lv.venta.service;

import lv.venta.model.Dalibnieks;

public interface IDalibnieksCRUDService {
	void izdzestDalibniekuPecId(int id) throws Exception;
	void jaunsDalibnieks(String loma, String lietotajvards, String parole) throws Exception;
	void atjaunotDalibniekuPecId(int id, String loma, String lietotajvards, String parole) throws Exception;
	Dalibnieks izveletiesDalibniekuPecId(int id) throws Exception;
}
