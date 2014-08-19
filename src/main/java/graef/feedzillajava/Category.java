package graef.feedzillajava;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {
	@XmlElement(name = "category_id")
	private final int id;
	@XmlElement(name = "display_category_name")
	private final String displayName = null;
	@XmlElement(name = "english_category_name")
	private final String englishName = null;
	@XmlElement(name = "url_category_name")
	private final String urlName = null;

	public Category(int id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	private Category() {
		this.id = 0;
	}

	public int getId() {
		return id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public String getUrlName() {
		return urlName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", displayName=" + displayName + "]";
	}

}
