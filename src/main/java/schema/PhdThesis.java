package schema;

// Generated Jul 13, 2012 6:22:22 AM by Hibernate Tools 3.4.0.CR1

/**
 * PhdThesis generated by hbm2java
 */
public class PhdThesis implements java.io.Serializable {

	private int id;
	private String thesisId;
	private String title;
	private String volume;
	private String number;
	private String ee;
	private Integer year;
	private String pages;
	private String url;
	private String publisher;
	private String school;
	private String month;
	private String note;
	private String isbn;
	private Integer authorId;

	public PhdThesis() {
	}

	public PhdThesis(int id, String thesisId) {
		this.id = id;
		this.thesisId = thesisId;
	}

	public PhdThesis(int id, String thesisId, String title, String volume,
			String number, String ee, Integer year, String pages, String url,
			String publisher, String school, String month, String note,
			String isbn, Integer authorId) {
		this.id = id;
		this.thesisId = thesisId;
		this.title = title;
		this.volume = volume;
		this.number = number;
		this.ee = ee;
		this.year = year;
		this.pages = pages;
		this.url = url;
		this.publisher = publisher;
		this.school = school;
		this.month = month;
		this.note = note;
		this.isbn = isbn;
		this.authorId = authorId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThesisId() {
		return this.thesisId;
	}

	public void setThesisId(String thesisId) {
		this.thesisId = thesisId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEe() {
		return this.ee;
	}

	public void setEe(String ee) {
		this.ee = ee;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getPages() {
		return this.pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

}
