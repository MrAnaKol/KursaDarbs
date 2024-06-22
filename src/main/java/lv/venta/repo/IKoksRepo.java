package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;
import lv.venta.model.Koks;

public interface IKoksRepo extends CrudRepository<Koks, Integer> {

	void deleteByIdK(int id);

	Koks findByIdK(int id);

}
