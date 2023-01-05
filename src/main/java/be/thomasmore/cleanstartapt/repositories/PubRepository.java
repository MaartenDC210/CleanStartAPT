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
    
    @Query("SELECT v FROM Venue v WHERE " +
            "(:minCapacity IS NULL OR :minCapacity <= v.capacity) AND " +
            "(:maxCapacity IS NULL OR v.capacity <= :maxCapacity) AND " +
            "(:maxDistance IS NULL OR v.distanceFromPublicTransportInKm <= :maxDistance) AND " +
            "(:foodProvided IS NULL OR v.foodProvided = :foodProvided) AND " +
            "(:indoor IS NULL OR v.indoor=:indoor) AND " +
            "(:outdoor IS NULL OR v.outdoor=:outdoor) ")
    List<Pub> findByFilter(@Param("minCapacity") Integer minCapacity,
                             @Param("maxCapacity") Integer maxCapacity,
                             @Param("maxDistance") Integer maxDistance,
                             @Param("foodProvided") Boolean foodProvided,
                             @Param("indoor") Boolean indoor,
                             @Param("outdoor") Boolean outdoor);
}