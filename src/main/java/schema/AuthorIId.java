package schema;

// Generated Jul 13, 2012 6:22:22 AM by Hibernate Tools 3.4.0.CR1

/**
 * AuthorIId generated by hbm2java
 */
public class AuthorIId implements java.io.Serializable {

	private int personId;
	private int inProcId;

	public AuthorIId() {
	}

	public AuthorIId(int personId, int inProcId) {
		this.personId = personId;
		this.inProcId = inProcId;
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getInProcId() {
		return this.inProcId;
	}

	public void setInProcId(int inProcId) {
		this.inProcId = inProcId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthorIId))
			return false;
		AuthorIId castOther = (AuthorIId) other;

		return (this.getPersonId() == castOther.getPersonId())
				&& (this.getInProcId() == castOther.getInProcId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPersonId();
		result = 37 * result + this.getInProcId();
		return result;
	}

}
