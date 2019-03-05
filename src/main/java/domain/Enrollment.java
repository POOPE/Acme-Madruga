
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Enrollment extends DomainEntity {

	private String		position;
	private Date		moment;
	private Boolean		memberIn;
	private Date		dropIn;

	private Member		member;
	private Brotherhood	broder;


	@NotBlank
	public String getPosition() {
		return this.position;
	}

	public void setPosition(final String position) {
		this.position = position;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public Boolean getMemberIn() {
		return this.memberIn;
	}

	public void setMemberIn(final Boolean memberIn) {
		this.memberIn = memberIn;
	}

	public Date getDropIn() {
		return this.dropIn;
	}

	public void setDropIn(final Date dropIn) {
		this.dropIn = dropIn;
	}

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = false)
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@ManyToOne(optional = false)
	public Brotherhood getBroder() {
		return this.broder;
	}

	public void setBroder(final Brotherhood broder) {
		this.broder = broder;
	}

}
