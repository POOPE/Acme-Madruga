
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Attachment extends DomainEntity {

	private Actor	owner;
	private String	URL;


	@ManyToOne(optional = false)
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	@NotBlank
	public String getURL() {
		return this.URL;
	}

	public void setURL(final String uRL) {
		this.URL = uRL;
	}

}
