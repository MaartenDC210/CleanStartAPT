package be.thomasmore.cleanstartapt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import be.thomasmore.cleanstartapt.model.Pub;

public interface PubRepository extends CrudRepository<Pub, Integer> {
    Iterable<Pub> findByHasGoodBeer(boolean hasGoodBeer);
    Iterable<Pub> findByHasFreeParking(boolean hasFreeParking);
    Iterable<Pub> findByCapacityBetween(int min, int max);
    Iterable<Pub> findByCapacityGreaterThan(int min);
    
    @Query("SELECT p FROM Pub p WHERE " +
            "(:minCapacity IS NULL OR :minCapacity <= p.capacity) AND " +
            "(:maxCapacity IS NULL OR p.capacity <= :maxCapacity) AND " +
            "(:maxDistance IS NULL OR p.distanceFromPublicTransportInKm <= :maxDistance) AND " +
            "(:hasGoodBeer IS NULL OR p.hasGoodBeer = :hasGoodBeer) AND " +
            "(:hasFreeParking IS NULL OR p.hasFreeParking =:hasFreeParking) AND " +
            "(:hasOutdoor IS NULL OR p.hasOutdoor=:hasOutdoor) ")
    List<Pub> findByFilter(@Param("minCapacity") Integer minCapacity,
                             @Param("maxCapacity") Integer maxCapacity,
                             @Param("maxDistance") Double maxDistance,
                             @Param("hasGoodBeer") Boolean hasGoodBeer,
                             @Param("hasFreeParking") Boolean hasFreeParking,
                             @Param("hasOutdoor") Boolean hasOutdoor);
}