
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Brotherhood;
import domain.Enrollment;
import domain.Member;
import repositories.EnrollmentRepository;

@Service
@Transactional
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository	enrollmentRepository;
	@Autowired
	private MemberService			memberService;


	public Enrollment create(final Brotherhood b) {

		final Enrollment w = new Enrollment();

		final Member member = this.memberService.findPrincipal();

		w.setMember(member);
		w.setBroder(b);
		w.setMoment(new Date());

		w.setStatus(Enrollment.PENDING);

		return w;
	}

	public Enrollment save(final Enrollment w) {
		if (w.getId() == 0) {

			w.setMoment(new Date());

			w.setStatus(Enrollment.PENDING);
		}
		return this.enrollmentRepository.save(w);
	}

	public Enrollment findById(final int enrollmentId) {
		return this.enrollmentRepository.findOne(enrollmentId);
	}

	public List<Enrollment> findByMember(final Member member) {
		return this.enrollmentRepository.findByMember(member.getId());
	}

	public List<Enrollment> findByBrotherhood(final Brotherhood brotherhood) {
		return this.enrollmentRepository.findByBrotherhood(brotherhood.getId());
	}

	public List<Enrollment> findAll() {
		return this.enrollmentRepository.findAll();
	}

	public Enrollment acceptEnrollment(final Enrollment e) {

		e.setMoment(new Date());
		e.setStatus(Enrollment.ACCEPTED);

		//y el position?
		return e;
	}

	public Enrollment rejectEnrollment(final Enrollment e) {

		e.setMoment(new Date());
		e.setStatus(Enrollment.DENIED);
		//crear nueva entrada en el historial
		return e;
	}

	public Boolean deleteEnrollment(final Enrollment e) {

		Boolean res = true;
		this.enrollmentRepository.delete(e);

		//crear nueva entrada en el historial

		if (this.enrollmentRepository.findOne(e.getId()) != null)
			res = false;

		return res;
	}

}
