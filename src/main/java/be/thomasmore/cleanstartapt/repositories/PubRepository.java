package be.thomasmore.cleanstartapt.repositories;

import org.springframework.data.repository.CrudRepository;

import be.thomasmore.cleanstartapt.model.Pub;

public interface PubRepository extends CrudRepository<Pub, Integer> {
    Iterable<Pub> findByHasGoodBeer(boolean hasGoodBeer);
    Iterable<Pub> findByHasFreeParking(boolean hasFreeParking);
    Iterable<Pub> findByCapacityBetween(int min, int max);
    Iterable<Pub> findByCapacityGreaterThan(int min);
}