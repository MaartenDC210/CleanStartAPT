package be.thomasmore.cleanstartapt.repositories;

import be.thomasmore.cleanstartapt.model.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
}
