/*
 * ActorService.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import forms.ActorForm;
import forms.RegisterForm;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository		actorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private MemberService		memberService;
	@Autowired
	private AdminService		adminService;
	@Autowired
	private BrotherhoodService	brotherhoodService;
	@Autowired
	private LoginService		loginService;


	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);

		Actor result;

		result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));

		this.actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------

	public UserAccount findUserAccount(final Actor actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = this.userAccountService.findByActor(actor);

		return result;
	}

	public void register(final RegisterForm registerForm) {
		final String role = registerForm.getRole();
		switch (role) {
		case "MEMBER":
			this.memberService.register(registerForm);
			break;
		case "ADMIN":
			this.adminService.findPrincipal();
			this.adminService.register(registerForm);
			break;
		case "BROTHERHOOD":
			this.brotherhoodService.register(registerForm);
			System.out.println("GO THIS FAR");
			break;
		}
	}

	public Actor edit(final ActorForm actorForm) {
		return this.edit(this.findPrincipal(), actorForm);
	}

	public Actor edit(final Actor actor, final ActorForm actorForm) {
		if (actorForm.getAddress() != null)
			actor.setAddress(actorForm.getAddress());
		if (actorForm.getAreaCode() != null)
			actor.setAreaCode(actorForm.getAreaCode());
		if (actorForm.getCountryCode() != null)
			actor.setCountryCode(actorForm.getCountryCode());
		if (actorForm.getPhoneNumber() != null)
			actor.setPhoneNumber(actorForm.getPhoneNumber());
		if (actorForm.getEmail() != null)
			actor.setEmail(actorForm.getEmail());
		if (actorForm.getFirstName() != null)
			actor.setName(actorForm.getFirstName());
		if (actorForm.getMiddleName() != null)
			actor.setMiddleName(actorForm.getMiddleName());
		if (actorForm.getLastName() != null)
			actor.setSurname(actorForm.getLastName());
		if (actorForm.getPhoto() != null)
			actor.setPhoto(actorForm.getPhoto());
		return this.save(actor);
	}

	public ActorForm generateForm(final Actor actor) {
		final ActorForm res = new ActorForm();
		res.setAddress(actor.getAddress());
		res.setAreaCode(actor.getAreaCode());
		res.setCountryCode(actor.getCountryCode());
		res.setEmail(actor.getEmail());
		res.setFirstName(actor.getName());
		res.setLastName(actor.getSurname());
		res.setMiddleName(actor.getMiddleName());
		res.setPhoneNumber(actor.getPhoneNumber());
		res.setPhoto(actor.getPhoto());
		return res;
	}

	public Actor initialize(final Actor actor, final String authority) {
		actor.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setUserAccount(this.userAccountService.createUserAccount(authority));

		return actor;
	}

	public void assertPrincipalAuthority(final String auth) {
		Assert.isTrue(this.getPrincipalAuthority().contains(auth), "The user logged does not have authority to do this action.");

	}

	public Actor findPrincipal() {
		return this.actorRepository.findByUser(LoginService.getPrincipal().getId());
	}

	public Collection<String> getPrincipalAuthority() {
		final Collection<Authority> auth = this.findPrincipal().getUserAccount().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}

	public Collection<String> getAuthority(final Actor actor) {
		final Collection<Authority> auth = actor.getUserAccount().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}
}
