package lv.venta.service;

import lv.venta.model.Koks;

public interface IKoksService {
	void izdzestKokuPecId(int id) throws Exception;
	Koks izveletiesKokuPecId(int id) throws Exception;
	void izmainitKokaAugstumu(int id, int skaits) throws Exception;
}
