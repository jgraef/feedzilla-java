package graef.feedzillajava;

import javax.xml.bind.annotation.XmlElement;

public class Enclosure {
	@XmlElement(name = "uri")
	private final String uri = null;
	@XmlElement(name = "media_type")
	private final String mediaType = null;
	@XmlElement(name = "length", required=false)
	private final long length = 0;

	private Enclosure() {
	}

	public String getUri() {
		return uri;
	}

	public String getMediaType() {
		return mediaType;
	}
	
	public long getLength() {
		return length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		Enclosure other = (Enclosure) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enclosure [uri=" + uri + ", mediaType=" + mediaType + "]";
	}
}
