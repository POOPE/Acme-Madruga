
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BrotherhoodRepository;
import domain.Brotherhood;
import forms.RegisterForm;

@Service
@Transactional
public class BrotherhoodService {

	@Autowired
	private BrotherhoodRepository	brotherhoodRepo;
	@Autowired
	private ActorService			actorService;


	public void register(final RegisterForm registerForm) {
		Brotherhood Brotherhood = new Brotherhood();
		Brotherhood = (Brotherhood) this.actorService.initialize(Brotherhood, registerForm.getRole());

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		Brotherhood.getUserAccount().setPassword(encoder.encodePassword(registerForm.getPassword(), null));
		Brotherhood.getUserAccount().setUsername(registerForm.getUsername());
		Brotherhood.setAddress(registerForm.getAddress());
		Brotherhood.setAreaCode(registerForm.getAreaCode());
		Brotherhood.setCountryCode(registerForm.getCountryCode());
		Brotherhood.setEmail(registerForm.getEmail());
		Brotherhood.setMiddleName(registerForm.getMiddleName());
		Brotherhood.setName(registerForm.getFirstName());
		Brotherhood.setPhoneNumber(registerForm.getPhoneNumber());
		Brotherhood.setPhoto(registerForm.getPhoto());
		Brotherhood.setSurname(registerForm.getLastName());

		this.save(Brotherhood);
	}

	public Brotherhood save(final Brotherhood Brotherhood) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Brotherhood res = this.brotherhoodRepo.save(Brotherhood);
		return res;

	}
}
