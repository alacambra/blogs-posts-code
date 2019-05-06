package tech.lacambla.blog.examples.matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.internal.progress.ThreadSafeMockingProgress.mockingProgress;

class ArticleTest {


  @Test
  void checkVerify() {

    Article article1 = new Article(1, "someText", "title");
    Article article2 = new Article(2, "someText", "title");
    Article article3 = new Article(3, "someText", "title");

    Publication publication = Mockito.spy(Publication.class);
    publication.addArticle(article1);

    Mockito.verify(publication).addArticle(ArticleMatcher.eq(article2));
    Mockito.verify(publication, Mockito.times(0)).addArticle(Mockito.eq(article3));

  }

  @Test
  void checkStub() {

    Article article1 = new Article(1, "someText", "title");
    Article article2 = new Article(2, "someText", "title");
    Article article3 = new Article(3, "someText", "title");

    Publication publication = Mockito.spy(Publication.class);
    Mockito.when(publication.getArticlesLike(ArticleMatcher.eq(article2))).thenReturn(Arrays.asList(article1, article2));

    List<Article> articles = publication.getArticlesLike(article3);
    Assertions.assertEquals(2, articles.size());
    Assertions.assertTrue(articles.contains(article1));
    Assertions.assertTrue(articles.contains(article2));
    Assertions.assertFalse(articles.contains(article3));
  }
}

class ArticleMatcher implements ArgumentMatcher<Article> {


  public final Article article;

  /**
   * register our matcher.
   */
  public static Article eq(Article article) {
    mockingProgress().getArgumentMatcherStorage().reportMatcher(new ArticleMatcher(article));
    return null;
  }

  public ArticleMatcher(Article article) {
    this.article = article;
  }

  /**
   * Implements matches method with our matching logic.
   * @param article
   * @return
   */
  @Override
  public boolean matches(Article article) {
    return this.article.getText().equalsIgnoreCase(article.getText());
  }

  public String toString() {
    return "<ArticleMatcher>";
  }
}