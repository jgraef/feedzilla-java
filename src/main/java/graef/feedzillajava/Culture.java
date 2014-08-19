package graef.feedzillajava;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Culture {
	@XmlElement(name = "culture_code")
	private final String code;
	@XmlElement(name = "display_culture_name")
	private final String displayName = null;
	@XmlElement(name = "english_culture_name")
	private final String englishName = null;
	@XmlElement(name = "country_code")
	private final String countryCode = null;
	@XmlElement(name = "language_code")
	private final String languageCode = null;

	public Culture(String code) {
		this.code = code;
	}

	@SuppressWarnings("unused")
	private Culture() {
		code = null;
	}

	public String getCode() {
		return code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getEnglishName() {
		return englishName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Culture other = (Culture) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Culture [code=" + code + "]";
	}
}
