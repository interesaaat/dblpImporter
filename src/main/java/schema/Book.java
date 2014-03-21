package schema;

// Generated Jul 13, 2012 6:22:22 AM by Hibernate Tools 3.4.0.CR1

/**
 * Book generated by hbm2java
 */
public class Book implements java.io.Serializable {

	private int id;
	private String bookId;
	private String title;
	private String volume;
	private String ee;
	private Integer year;
	private String pages;
	private String url;
	private String publisher;
	private String booktitle;
	private String cite;
	private String isbn;
	private String series;
	private String cdrom;
	private String month;

	public Book() {
	}

	public Book(int id, String bookId) {
		this.id = id;
		this.bookId = bookId;
	}

	public Book(int id, String bookId, String title, String volume, String ee,
			Integer year, String pages, String url, String publisher,
			String booktitle, String cite, String isbn, String series,
			String cdrom, String month) {
		this.id = id;
		this.bookId = bookId;
		this.title = title;
		this.volume = volume;
		this.ee = ee;
		this.year = year;
		this.pages = pages;
		this.url = url;
		this.publisher = publisher;
		this.booktitle = booktitle;
		this.cite = cite;
		this.isbn = isbn;
		this.series = series;
		this.cdrom = cdrom;
		this.month = month;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
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

	public String getBooktitle() {
		return this.booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getCite() {
		return this.cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getCdrom() {
		return this.cdrom;
	}

	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}