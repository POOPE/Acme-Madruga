
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Enrollment extends DomainEntity {

	private Position			position;
	private Date				moment;
	private String				status;

	private Member				member;
	private Brotherhood			broder;

	public static final String	PENDING		= "PENDING";
	public static final String	ACCEPTED	= "ACCEPTED";
	public static final String	DENIED		= "DENIED";


	@ManyToOne(optional = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(final Position position) {
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

	@Pattern(regexp = "^" + Enrollment.PENDING + "|" + Enrollment.ACCEPTED + "|" + Enrollment.DENIED + "$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
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
