package be.repositories;

import org.springframework.data.repository.CrudRepository;

import be.thomasmore.cleanstartapt.model.Pub;

public interface PubRepository extends CrudRepository<Pub, Integer> {
    Iterable<Pub> findByHasGoodBeer(boolean hasGoodBeer);
}