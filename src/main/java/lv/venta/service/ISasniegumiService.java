package lv.venta.service;

import lv.venta.model.Sasniegumi;

public interface ISasniegumiService {
	void izmainitPupinuSkaitu(int id, int skaits) throws Exception;
	void izmainitPupinuSkaituParKlikski(int id, int skaits) throws Exception;
	void izmainitAutonomuPupinuSkaitu(int id, int skaits) throws Exception;
	Sasniegumi izveletiesSasniegumusPecId(int id) throws Exception;
	void tiekUzspiestsUzKoka(int id) throws Exception;
	void autonomasPupinas(int id) throws Exception;
}
