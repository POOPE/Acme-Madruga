
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Enrollment;
import repositories.EnrollmentRepository;

@Service
@Transactional
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository	enrollmentRepository;
	@Autowired
	private ActorService			actorService;


	public Enrollment create() {
		final Enrollment w = new Enrollment();
		w.setMemberIn(false);
		return w;
	}

	public Enrollment save(Enrollment w) {
		if (w.getId() == 0)
			w = this.initialize(w);
		return this.warrantyRepository.save(w);
	}

	public Enrollment findById(final int id) {
		return this.warrantyRepository.findOne(id);
	}

	public List<Enrollment> findAll() {
		return this.warrantyRepository.findAll();
	}

	public void delete(final Enrollment w) {

		this.warrantyRepository.delete(w.getId());
	}

	public Enrollment initialize(final Enrollment warranty) {
		warranty.setLocked(false);
		return warranty;
	}

	public List<Enrollment> findAllFinal() {
		return this.warrantyRepository.findAllFinal();
	}

}
