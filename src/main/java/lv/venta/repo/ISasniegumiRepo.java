package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;
import lv.venta.model.Sasniegumi;

public interface ISasniegumiRepo extends CrudRepository<Sasniegumi, Integer> {

	Sasniegumi findByIdS(int id);

}
