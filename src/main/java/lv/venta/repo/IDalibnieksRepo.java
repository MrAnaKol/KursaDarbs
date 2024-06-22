package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Dalibnieks;

public interface IDalibnieksRepo extends CrudRepository<Dalibnieks, Integer> {

	void deleteByIdD(int id);

	Dalibnieks findByIdD(int id);

	Dalibnieks findByLietotajvards(String lietotajvards);

	Dalibnieks findByLietotajvardsAndParole(String lietotajvards, String parole);
}
