package graef.feedzillajava;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FeedZilla {
	public static final String BASE_URL = "http://api.feedzilla.com/v1/";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat
			.forPattern("yyyy-MM-dd");
	private final URI baseUrl;
	private WebTarget root;
	private Client client;
	private String clientSource = "graef.feedzilla-java";

	public FeedZilla() {
		try {
			baseUrl = new URI(BASE_URL);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		JerseyClientBuilder clientBuilder = new JerseyClientBuilder();
		clientBuilder.hostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return hostname.equals(baseUrl.getHost());
			}
		});
		client = clientBuilder.build();
		client.register(JacksonFeature.class);
		root = client.target(baseUrl);
	}

	public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}

	public Collection<Culture> getCultures() {
		return root.path("cultures.json").request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Culture>>() {
				});
	}

	public List<Category> getCategories(Culture culture, CategoryOrder order) {
		WebTarget target = root.path("categories.json");
		if (culture != null) {
			target = target.queryParam("culture_code", culture.getCode());
		}
		if (order != null) {
			target = target.queryParam("order", order.name().toLowerCase());
		}
		return target.request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Category>>() {
				});
	}

	public List<Category> getCategories(CategoryOrder order) {
		return getCategories(null, order);
	}

	public List<Category> getCategories() {
		return getCategories(null, CategoryOrder.NONE);
	}

	public List<Subcategory> getSubcategories(Category category,
			Culture culture, CategoryOrder order) {
		WebTarget target = root;
		if (category != null) {
			target = target.path("categories").path(
					Integer.toString(category.getId()));
		}
		target = target.path("subcategories.json");
		if (culture != null) {
			target = target.queryParam("culture_code", culture.getCode());
		}
		if (order != null) {
			target = target.queryParam("order", order.name().toLowerCase());
		}
		return target.request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Subcategory>>() {
				});
	}

	public List<Subcategory> getSubcategories(Category category,
			CategoryOrder order) {
		return getSubcategories(category, null, order);
	}

	public List<Subcategory> getSubcategories(CategoryOrder order) {
		return getSubcategories(null, null, order);
	}

	public List<Subcategory> getSubcategories(Category category) {
		return getSubcategories(category, null, CategoryOrder.NONE);
	}

	public List<Subcategory> getSubcategories() {
		return getSubcategories(null, null, CategoryOrder.NONE);
	}

	private Articles queryArticles(Category category, Subcategory subcategory,
			String query, int count, DateTime since, ArticleOrder order,
			boolean titleOnly, Culture culture) {
		WebTarget target = root;

		if (category != null) {
			target = target.path("categories").path(
					Integer.toString(category.getId()));
			if (subcategory != null) {
				target = target.path("subcategories").path(
						Integer.toString(subcategory.getId()));
			}
		}
		if (query != null) {
			target = target.path("articles").path("search.json")
					.queryParam("q", query);
		} else {
			target = target.path("articles.json");
		}
		if (count != 0) {
			target = target.queryParam("count", count);
		}
		if (since != null) {
			target = target.queryParam("since", since
					.withZone(DateTimeZone.UTC).toString(DATE_FORMATTER));
		}
		if (order != null) {
			target = target.queryParam("order", order.name().toLowerCase());
		}
		target = target.queryParam("clientSource", clientSource).queryParam(
				"title_only", titleOnly ? 1 : 0);
		if (category == null && culture != null) {
			target = target.queryParam("culture_code", culture.getCode());
		}
		System.out.println(target.getUri().toString());
		Articles articles = target.request().accept(MediaType.APPLICATION_JSON)
				.get(Articles.class);
		return articles;
	}

	public QueryBuilder query() {
		return new QueryBuilder(this);
	}

	public class QueryBuilder {
		private final FeedZilla feedZilla;
		private Category category = null;
		private Subcategory subcategory = null;
		private int count = 0;
		private DateTime since = null;
		private ArticleOrder order = null;
		private boolean titleOnly = false;
		private Culture culture = null;

		protected QueryBuilder(FeedZilla feedZilla) {
			this.feedZilla = feedZilla;
		}

		public Articles articles() {
			if (category == null) {
				throw new IllegalStateException(
						"articles must not be null, when querying all articles");
			}
			return feedZilla.queryArticles(category, subcategory, null, count,
					since, order, titleOnly, null);
		}

		public Articles search(String query) {
			return feedZilla.queryArticles(category, subcategory, query, count,
					since, order, titleOnly, culture);
		}

		public Category getCategory() {
			return category;
		}

		public QueryBuilder category(Category category) {
			this.category = category;
			return this;
		}

		public Subcategory getSubcategory() {
			return subcategory;
		}

		public QueryBuilder subcategory(Subcategory subcategory) {
			this.subcategory = subcategory;
			return this;
		}

		public int getCount() {
			return count;
		}

		public QueryBuilder count(int count) {
			if (count < 0 || count > 100) {
				throw new IllegalArgumentException(
						"count must be between 0 and 100 (inclusive");
			}
			this.count = count;
			return this;
		}

		public DateTime getSince() {
			return since;
		}

		public QueryBuilder since(DateTime since) {
			this.since = since;
			return this;
		}

		public ArticleOrder getOrder() {
			return order;
		}

		public QueryBuilder order(ArticleOrder order) {
			this.order = order;
			return this;
		}

		public boolean isTitleOnly() {
			return titleOnly;
		}

		public QueryBuilder titleOnly(boolean titleOnly) {
			this.titleOnly = titleOnly;
			return this;
		}

		public Culture getCulture() {
			return culture;
		}

		public QueryBuilder culture(Culture culture) {
			this.culture = culture;
			return this;
		}
	}
}
