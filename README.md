feedzilla-java
==============

Install
-------

1. Build:

```
$ git clone https://github.com/jgraef/feedzilla-java.git
$ cd feedzilla-java
$ mvn install
```

2. Integrate into your project: Insert into your pom.xml

```
		<dependency>
		<groupId>graef</groupId>
		<artifactId>feedzilla-java</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		</dependency>
```

3. Get a FeedZilla instance

```java
FeedZilla feed = new FeedZilla();
Articles articles = feed.query().count(10).search("filezilla");
for (Article article: articles.getArticles()) {
  System.out.println(article.getTitle());
}
```

