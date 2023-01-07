package be.thomasmore.cleanstartapt.repositories;

import be.thomasmore.cleanstartapt.model.Party;
import be.thomasmore.cleanstartapt.model.Pub;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, Integer> {
    Iterable<Party> findByPub(Pub pub);
}
