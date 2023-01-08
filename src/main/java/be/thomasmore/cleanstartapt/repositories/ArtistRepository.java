package be.thomasmore.cleanstartapt.repositories;

import be.thomasmore.cleanstartapt.model.Artist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends CrudRepository<Artist, Integer>
{
    List<Artist> findAllBy();
    List<Artist> findByArtistNameContainingIgnoreCase(String keyword);

    @Query("select a from Artist a WHERE " +
            ":keyword IS NULL OR " +
            "(UPPER(a.artistName) LIKE UPPER(CONCAT('%', :keyword, '%'))) OR " +
            "(UPPER(a.bio) LIKE UPPER(CONCAT('%', :keyword, '%'))) OR " +
            "(UPPER(a.genre) LIKE UPPER(CONCAT('%', :keyword, '%'))) OR " +
            "(UPPER(a.portfolio) LIKE UPPER(CONCAT('%', :keyword, '%')))")
    List<Artist> findByKeyword(@Param("keyword")String keyword);

    List<Artist> findByIdIn(@Param("ids") Integer[] ids);

    default List<Artist> findByIdInIfNotNull(@Param("ids") Integer[] ids) {
        if (ids != null) return findByIdIn(ids);
        return new ArrayList<>();
    }
}
