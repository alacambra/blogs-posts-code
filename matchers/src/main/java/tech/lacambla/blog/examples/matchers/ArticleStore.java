package tech.lacambla.blog.examples.matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleStore {

  private Map<Integer, Article> articles;
  private DuplicationService duplicationService;

  public ArticleStore(DuplicationService duplicationService) {
    this.duplicationService = duplicationService;
    articles = new HashMap<>();
  }

  public Article createArticle(String text, String title) {
    Article article = new Article(getNextId(), text, title);
    storeArticle(article);
    return article;
  }

  public Article createCopy(int id) {
    Article article = articles.get(id);
    Article copy = duplicationService.duplicate(getNextId(), article);
    storeArticle(copy);

    return copy;
  }

  public List<Article> getArticlesLike(Article article) {
    return articles.values()
        .stream()
        .filter(a -> a.getText().equalsIgnoreCase(article.getText()) && a.getTitle().equalsIgnoreCase(article.getTitle()))
        .collect(Collectors.toList());
  }

  void storeArticle(Article article) {
    articles.put(article.getId(), article);
  }

  private Integer getNextId() {
    return articles.size() + 1;
  }
}
