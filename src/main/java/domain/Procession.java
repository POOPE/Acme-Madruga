
package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Procession extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String ticker;
	private Date moment;
	private Boolean	isInFinalMode;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^([0-9]{2})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])(-)([A-Z]{5})$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	private String	title;
	private String	description;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Boolean getIsInFinalMode() {
		return this.isInFinalMode;
	}

	public void setIsInFinalMode(final Boolean isInFinalMode) {
		this.isInFinalMode = isInFinalMode;
	}
	

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	
	// Relationships ----------------------------------------------------------
	private Brotherhood	brotherhood;



	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Brotherhood	getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}



	// Methods ----------------------------------------------------------
	public String generateTicker() {
		String ticker = "";
		final DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		final Date date = new Date();
		final String tickerDate = (dateFormat.format(date));
		final String tickerAlphanumeric = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		ticker = ticker.concat(tickerDate).concat("-").concat(tickerAlphanumeric);
		return ticker;
	}

	@Override
	public String toString() {
		return "Procession [ticker=" + this.ticker  + "title=" + this.title + "]";
	}
}
