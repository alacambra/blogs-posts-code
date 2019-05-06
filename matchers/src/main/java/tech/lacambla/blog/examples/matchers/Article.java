package tech.lacambla.blog.examples.matchers;

import java.util.Objects;

public class Article {

  private int id;
  private String title;
  private String text;

  public Article(int id, String title, String text) {
    this.id = id;
    this.title = title;
    this.text = text;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }


  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return id == article.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
