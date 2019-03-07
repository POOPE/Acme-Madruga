
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class BrotherhoodFloat extends DomainEntity {

	private String	title;
	private String	description;
	private Brotherhood brotherhood;


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

	@NotBlank
	@ManyToOne(optional=false)
	public Brotherhood  getBrotherhood(){
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood){
		this.brotherhood = brotherhood;
	}
	
	@Override
	public String toString() {
		return "BrotherhoodFloat [title=" + this.title  + "description=" + this.description + "]";
	}


}
