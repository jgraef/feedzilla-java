package graef.feedzillajava;

import javax.xml.bind.annotation.XmlElement;

public class Enclosure {
	@XmlElement(name = "uri")
	private final String uri = null;
	@XmlElement(name = "media_type")
	private final String mediaType = null;

	private Enclosure() {
	}

	public String getUri() {
		return uri;
	}

	public String getMediaType() {
		return mediaType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mediaType == null) ? 0 : mediaType.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Enclosure other = (Enclosure) obj;
		if (mediaType == null) {
			if (other.mediaType != null) return false;
		}
		else if (!mediaType.equals(other.mediaType)) return false;
		if (uri == null) {
			if (other.uri != null) return false;
		}
		else if (!uri.equals(other.uri)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enclosure [uri=" + uri + ", mediaType=" + mediaType + "]";
	}
}
