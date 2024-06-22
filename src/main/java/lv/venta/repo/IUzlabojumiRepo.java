package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;
import lv.venta.model.Uzlabojumi;

public interface IUzlabojumiRepo extends CrudRepository<Uzlabojumi, Integer> {

	void deleteByIdU(int id);

	Uzlabojumi findByIdU(int id);

}
