package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.UzlabojumaTips;
import lv.venta.model.Uzlabojumi;

public interface IUzlabojumiService {
	void izdzestUzlabojumuPecId(int id) throws Exception;
	void nopirktUzlabojumu(int idU, int idD) throws Exception;
	Uzlabojumi izveletiesUzlabojumuPecId(int id) throws Exception;
	void jaunsUzlabojums(String nosaukums, int cena, UzlabojumaTips uzlabojumaTips) throws Exception;
	ArrayList<Uzlabojumi> izveletiesVisusNeizmantotusUzlabojumus(int id) throws Exception;
	Uzlabojumi izveletiesUzlabojumuPecNosaukumaUnCenas(String nosaukums, int cena) throws Exception;
}
