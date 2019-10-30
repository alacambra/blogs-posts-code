package tech.lacambla.blog.examples.matchers;

public class DuplicationService {

  public Article duplicate(int nextId, Article article) {
    return new Article(nextId, article.getTitle(), article.getText());
  }

}
