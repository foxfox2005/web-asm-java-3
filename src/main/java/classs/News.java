package classs;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "News.findById", query = "SELECT o FROM News o WHERE o.id = :id"),
		@NamedQuery(name = "News.findAll", query = "FROM News") })
@Entity
@Table(name = "NEWS")
public class News {

	private String id;
	private String categoriesId;
	private String usersId;
	private String title;
	private String content;
	private String summary;
	private String image;
	private Date postedDate;
	private Integer viewCount;
	private Boolean home;

	public News() {
	}

	public News(String id, String usersId, String title, String content, Date postedDate, String summary) {
		this.id = id;
		this.usersId = usersId;
		this.title = title;
		this.content = content;
		this.postedDate = postedDate;
		this.summary = summary;
	}

	public News(String id, String categoriesId, String usersId, String title, String content, String image,
			Date postedDate, Integer viewCount, Boolean home, String summary) {
		this.id = id;
		this.categoriesId = categoriesId;
		this.usersId = usersId;
		this.title = title;
		this.content = content;
		this.image = image;
		this.postedDate = postedDate;
		this.viewCount = viewCount;
		this.home = home;
		this.summary = summary;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public String getCategoriesId() {
		return this.categoriesId;
	}

	public void setCategoriesId(String categoriesId) {
		this.categoriesId = categoriesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author", nullable = false)
	public String getUsersId() {
		return this.usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "posted_date", nullable = false, length = 10)
	public Date getPostedDate() {
		return this.postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	@Column(name = "view_count")
	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	@Column(name = "Home")
	public Boolean getHome() {
		return this.home;
	}

	public void setHome(Boolean home) {
		this.home = home;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
