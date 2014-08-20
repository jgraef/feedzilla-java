package graef.feedzillajava;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Articles {
	@XmlElement(name = "articles")
	private final List<Article> articles = null;
	@XmlElement(name = "syndication_url", required = false)
	private final String syndicationUrl = null;
	@XmlElement(name = "title", required = false)
	private final String title = null;
	@XmlElement(name = "description", required = false)
	private final String description = null;

	private Articles() {
	}

	public List<Article> getArticles() {
		return articles;
	}

	public String getSyndicationUrl() {
		return syndicationUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}