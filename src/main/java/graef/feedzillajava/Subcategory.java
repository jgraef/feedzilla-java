package graef.feedzillajava;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subcategory {
	@XmlElement(name = "subcategory_id")
	private final int id;
	@XmlElement(name = "category_id")
	private final int parentId = 0;
	@XmlElement(name = "display_subcategory_name")
	private final String displayName = null;
	@XmlElement(name = "english_subcategory_name")
	private final String englishName = null;
	@XmlElement(name = "url_subcategory_name")
	private final String urlName = null;

	public Subcategory(int id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	private Subcategory() {
		this.id = 0;
	}

	public int getId() {
		return id;
	}

	public int getParentId() {
		return parentId;
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
		Subcategory other = (Subcategory) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", parentId=" + parentId
				+ ", displayName=" + displayName + "]";
	}
}
