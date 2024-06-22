package lv.venta.service;

import lv.venta.model.UzlabojumaTips;
import lv.venta.model.Uzlabojumi;

public interface IUzlabojumiService {
	void izdzestUzlabojumuPecId(int id) throws Exception;
	void nopirktUzlabojumu(int idU, int idD, int cena) throws Exception;
	Uzlabojumi izveletiesUzlabojumuPecId(int id) throws Exception;
	void jaunsUzlabojums(String nosaukums, int cena, UzlabojumaTips uzlabojumaTips) throws Exception;
}
