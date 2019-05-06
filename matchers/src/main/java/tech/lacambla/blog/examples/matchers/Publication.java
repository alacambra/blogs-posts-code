package tech.lacambla.blog.examples.matchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Publication {

  private List<Article> articles;

  public Publication() {
    articles = new ArrayList<>();
  }

  public Publication addArticle(Article article) {
    articles.add(article);
    return this;
  }

  public List<Article> getArticlesLike(Article article) {
    return articles
        .stream()
        .filter(a -> a.getText().equalsIgnoreCase(article.getText()) && a.getTitle().equalsIgnoreCase(article.getTitle()))
        .collect(Collectors.toList());
  }
}
