
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FloatPicture;

@Repository
public interface FloatPictureRepository extends JpaRepository<FloatPicture, Integer> {

	@Query("select a from FloatPicture a where a.owner.id=?1")
	public List<FloatPicture> findByFloat(int id);

}
