
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BrotherhoodRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Enrollment;
import domain.Member;
import forms.RegisterForm;

@Service
@Transactional
public class BrotherhoodService {

	@Autowired
	private BrotherhoodRepository	brotherhoodRepo;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private AttachmentService		attachmentService;
	@Autowired
	private BrotherhoodFloatService	bFloatService;
	@Autowired
	private EnrollmentService		enrollmentService;


	public List<Member> findMembers(final Brotherhood brotherhood) {
		final List<Member> res = new ArrayList<>();
		final List<Enrollment> enrollments = this.enrollmentService.findAcceptedByBrotherhood(brotherhood);
		for (final Enrollment enrollment : enrollments)
			res.add(enrollment.getMember());
		return res;
	}

	public Brotherhood findById(final int id) {
		return this.brotherhoodRepo.findOne(id);
	}

	public List<Brotherhood> findAll() {
		return this.brotherhoodRepo.findAll();
	}

	public Brotherhood findPrincipal() {
		this.actorService.assertPrincipalAuthority("BROTHERHOOD");
		return (Brotherhood) this.actorService.findPrincipal();
	}

	public void register(final RegisterForm registerForm) {
		Brotherhood brotherhood = new Brotherhood();
		brotherhood = (Brotherhood) this.actorService.initialize(brotherhood, registerForm.getRole());

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		brotherhood.getUserAccount().setPassword(encoder.encodePassword(registerForm.getPassword(), null));
		brotherhood.getUserAccount().setUsername(registerForm.getUsername());
		brotherhood.setAddress(registerForm.getAddress());
		brotherhood.setAreaCode(registerForm.getAreaCode());
		brotherhood.setCountryCode(registerForm.getCountryCode());
		brotherhood.setEmail(registerForm.getEmail());
		brotherhood.setMiddleName(registerForm.getMiddleName());
		brotherhood.setName(registerForm.getFirstName());
		brotherhood.setPhoneNumber(registerForm.getPhoneNumber());
		if (registerForm.getPhoto() != null && registerForm.getPhoto() != "")
			brotherhood.setPhoto(registerForm.getPhoto());
		brotherhood.setSurname(registerForm.getLastName());
		brotherhood.setTitle(registerForm.getTitle());
		brotherhood.setEstDate(new Date());
		final Actor saved = this.save(brotherhood);

		this.attachmentService.createAll(Arrays.asList(registerForm.getPhotos().split(",")), saved);
	}
	public Brotherhood save(final Brotherhood brotherhood) {
		return this.brotherhoodRepo.save(brotherhood);

	}

}
