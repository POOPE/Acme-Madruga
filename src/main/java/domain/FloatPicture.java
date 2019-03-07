
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class FloatPicture extends DomainEntity {

	private String	url;
	private BrotherhoodFloat  bFloat;

	@NotBlank
	@URL
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	
	@Valid
	@ManyToOne(optional=true,cascade = CascadeType.ALL)
	public BrotherhoodFloat  getBFloat(){
		return this.bFloat;
	}

	public void setBFloat(final BrotherhoodFloat  bFloat){
		this.bFloat = bFloat;
	}
	
	@Override
	public String toString() {
		return "FloatPicture [url=" + this.url  + "]";
	}

}
