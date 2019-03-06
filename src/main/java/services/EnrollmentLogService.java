
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EnrollmentLogRepository;
import domain.Brotherhood;
import domain.EnrollmentLog;
import domain.Member;

@Service
@Transactional
public class EnrollmentLogService {

	@Autowired
	private EnrollmentLogRepository	enrollmentLogRepository;
	@Autowired
	private MemberService			memberService;
	@Autowired
	private BrotherhoodService		brotherhoodService;


	public List<EnrollmentLog> findAll() {
		return this.enrollmentLogRepository.findAll();
	}

	public List<EnrollmentLog> findByMember(final Member member) {
		return this.enrollmentLogRepository.findByMember(member.getId());
	}

	public List<EnrollmentLog> findByBrotherhood(final Brotherhood brotherhood) {
		return this.enrollmentLogRepository.findByBrotherhood(brotherhood.getId());
	}

	public EnrollmentLog joins(final Brotherhood brotherhood, final Member member) {
		return this.create(brotherhood, member, "JOINS");
	}

	public EnrollmentLog leaves(final Brotherhood brotherhood, final Member member) {
		return this.create(brotherhood, member, "LEAVES");
	}

	public EnrollmentLog create(final Brotherhood brotherhood, final Member member, final String action) {
		final EnrollmentLog res = new EnrollmentLog();
		res.setBrotherhood(brotherhood);
		res.setMember(member);
		res.setAction(action);
		res.setMoment(new Date());
		return res;
	}

	public EnrollmentLog save(final EnrollmentLog enrollmentLog) {
		return this.enrollmentLogRepository.save(enrollmentLog);
	}

}
