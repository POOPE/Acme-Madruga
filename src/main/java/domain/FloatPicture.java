
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class FloatPicture extends DomainEntity {

	private String				url;
	private BrotherhoodFloat	owner;


	@NotBlank
	@URL
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@ManyToOne(optional = false)
	public BrotherhoodFloat getOwner() {
		return this.owner;
	}

	public void setOwner(final BrotherhoodFloat bFloat) {
		this.owner = bFloat;
	}

	@Override
	public String toString() {
		return "FloatPicture [url=" + this.url + "]";
	}

}
