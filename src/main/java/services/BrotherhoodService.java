
package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BrotherhoodRepository;
import domain.Actor;
import domain.Brotherhood;
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
	private BrotherhoodFloatService		bFloatService;


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

		if (registerForm.getPhotos() != null && registerForm.getPhotos() != "") {
			final String[] photos = registerForm.getPhotos().split(",");
			for (int i = 0; i < photos.length; i++)
				this.attachmentService.create(saved, photos[i]);

		}
	}
	public Brotherhood save(final Brotherhood brotherhood) {
		return this.brotherhoodRepo.save(brotherhood);

	}

	public Brotherhood findByBFloat(final int bFloatId){
		return this.bFloatService.findById(bFloatId).getBrotherhood();
	}
}
