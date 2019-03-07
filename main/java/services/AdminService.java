
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministratorRepository;
import security.UserAccountService;
import domain.Administrator;
import forms.RegisterForm;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;
	@Autowired
	private ActorService			actorService;


	public Administrator findPrincipal() {
		this.actorService.assertPrincipalAuthority("ADMIN");
		return (Administrator) this.actorService.findPrincipal();
	}

	public void register(final RegisterForm registerForm) {
		Administrator admin = new Administrator();
		admin = (Administrator) this.actorService.initialize(admin, registerForm.getRole());

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		admin.getUserAccount().setPassword(encoder.encodePassword(registerForm.getPassword(), null));
		admin.getUserAccount().setUsername(registerForm.getUsername());
		admin.setAddress(registerForm.getAddress());
		admin.setAreaCode(registerForm.getAreaCode());
		admin.setCountryCode(registerForm.getCountryCode());
		admin.setEmail(registerForm.getEmail());
		admin.setMiddleName(registerForm.getMiddleName());
		admin.setName(registerForm.getFirstName());
		admin.setPhoneNumber(registerForm.getPhoneNumber());
		admin.setPhoto(registerForm.getPhoto());
		admin.setSurname(registerForm.getLastName());

		this.save(admin);
	}

	public Administrator save(final Administrator admin) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Administrator res = this.adminRepository.save(admin);
		return res;

	}
}
