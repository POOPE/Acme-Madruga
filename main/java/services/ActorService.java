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
		}
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
