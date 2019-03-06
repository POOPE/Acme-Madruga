
package domain;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class EnrollmentLog {

	private Member				member;
	private Brotherhood			brotherhood;
	private String				action;
	private Date				moment;

	public static final String	JOINS	= "JOINS";
	public static final String	LEAVES	= "LEAVES";


	@ManyToOne
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@ManyToOne
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

	@Pattern(regexp = "^" + EnrollmentLog.JOINS + "|" + EnrollmentLog.LEAVES + "$")
	public String getAction() {
		return this.action;
	}

	public void setAction(final String action) {
		this.action = action;
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

}
