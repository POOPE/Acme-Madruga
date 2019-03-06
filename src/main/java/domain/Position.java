
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	private String	Title;


	@NotBlank
	public String getTitle() {
		return this.Title;
	}

	public void setTitle(final String title) {
		this.Title = title;
	}

}
