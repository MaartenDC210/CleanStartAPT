package be.thomasmore.cleanstartapt.repositories;

import be.thomasmore.cleanstartapt.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer>
{

}
