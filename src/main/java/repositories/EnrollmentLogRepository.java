
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.EnrollmentLog;

@Repository
public interface EnrollmentLogRepository extends JpaRepository<EnrollmentLog, Integer> {

	@Query("select a from EnrollmentLog a where a.member.id = ?1")
	public List<EnrollmentLog> findByMember(int id);

	@Query("select a from EnrollmentLog a where a.brotherhood.id=?1")
	public List<EnrollmentLog> findByBrotherhood(int id);
}
