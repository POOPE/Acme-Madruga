
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MemberRepository;
import domain.Member;
import forms.RegisterForm;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository	memberRepo;
	@Autowired
	private ActorService		actorService;


	public void register(final RegisterForm registerForm) {
		Member member = new Member();
		member = (Member) this.actorService.initialize(member, registerForm.getRole());

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		member.getUserAccount().setPassword(encoder.encodePassword(registerForm.getPassword(), null));
		member.getUserAccount().setUsername(registerForm.getUsername());
		member.setAddress(registerForm.getAddress());
		member.setAreaCode(registerForm.getAreaCode());
		member.setCountryCode(registerForm.getCountryCode());
		member.setEmail(registerForm.getEmail());
		member.setMiddleName(registerForm.getMiddleName());
		member.setName(registerForm.getFirstName());
		member.setPhoneNumber(registerForm.getPhoneNumber());
		member.setPhoto(registerForm.getPhoto());
		member.setSurname(registerForm.getLastName());

		this.save(member);
	}

	public Member save(final Member member) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Member res = this.memberRepo.save(member);
		return res;

	}
}
