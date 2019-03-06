/*
 * ActorRepository.java
 *
 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	@Query("select e from Enrollment e where e.author.id = ?1")
	public List<Enrollment> findByAuthor(int id);

	//	@Query("select a from Actor a where a.userAccount.id = ?1")
	//	Actor findByUser(int userId);

}
