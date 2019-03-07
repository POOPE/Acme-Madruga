
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	private Actor				author;
	private Procession			procession;
	private Integer				posColumn;
	private Integer				posRow;
	private String				status;
	private String				comment;

	public static final String	PENDING		= "PENDING";
	public static final String	ACCEPTED	= "ACCEPTED";
	public static final String	DENIED		= "DENIED";


	@NotNull
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
	}

	@NotNull
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

	@NotNull
	@Min(0)
	public Integer getPosColumn() {
		return this.posColumn;
	}

	public void setPosColumn(final Integer posColumn) {
		this.posColumn = posColumn;
	}

	@NotNull
	@Min(0)
	public Integer getPosRow() {
		return this.posRow;
	}

	public void setPosRow(final Integer posRow) {
		this.posRow = posRow;
	}

	@Pattern(regexp = "^" + Request.PENDING + "|" + Request.ACCEPTED + "|" + Request.DENIED + "$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

}
