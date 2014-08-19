package graef.feedzillajava;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@XmlRootElement
public class Article {
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormat
			.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");

	@XmlElement(name = "author")
	private final String author = null;

	@XmlElement(name = "publish_date")
	private String publishDateStr = null;
	private DateTime publishDate = null;

	@XmlElement(name = "source")
	private final String source = null;
	@XmlElement(name = "source_url")
	private final String sourceUrl = null;
	@XmlElement(name = "summary")
	private final String summary = null;
	@XmlElement(name = "title")
	private final String title = null;
	@XmlElement(name = "url")
	private final String url = null;
	@XmlElement(name = "enclosures", required = false)
	private final List<Enclosure> enclosures = null;

	private Article() {
	}

	public String getAuthor() {
		return author;
	}

	public DateTime getPublishDate() {
		if (publishDate == null && publishDateStr != null) {
			publishDate = DATETIME_FORMATTER.parseDateTime(publishDateStr);
			System.out.println(publishDateStr + " = " + publishDate);
			publishDateStr = null;
		}
		return publishDate;
	}

	public String getSource() {
		return source;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public String getSummary() {
		return summary;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public List<Enclosure> getEnclosures() {
		return Collections.unmodifiableList(enclosures);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result
				+ ((sourceUrl == null) ? 0 : sourceUrl.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (sourceUrl == null) {
			if (other.sourceUrl != null)
				return false;
		} else if (!sourceUrl.equals(other.sourceUrl))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [author=" + author + ", title=" + title + ", url="
				+ url + ", enclosures=" + enclosures + "]";
	}
}
