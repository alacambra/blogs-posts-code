package tech.lacambla.blog.examples.matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.internal.progress.ThreadSafeMockingProgress.mockingProgress;

class ArticleTest {

  DuplicationService duplicationService;
  ArticleStore articleStore;

  @BeforeEach
  void init() {
    duplicationService = Mockito.spy(DuplicationService.class);
    articleStore = Mockito.spy(new ArticleStore(duplicationService));
  }


  @Test
  void checkVerify() {

    Article article1 = new Article(1, "someText", "title");
    Article article2 = new Article(2, "someText", "title");
    Article article3 = new Article(3, "someText", "title");

    articleStore.storeArticle(article1);

    Mockito.verify(articleStore).storeArticle(ArticleMatcher.isDuplicateOf(article2));
    Mockito.verify(articleStore, times(0)).storeArticle(Mockito.eq(article3));

  }

  @Test
  void createArticle() {

    articleStore.createArticle("someText", "title");
    Article article = new Article(1, "someText", "title");

    Mockito.verify(articleStore).storeArticle(Mockito.eq(article));
    Mockito.verify(articleStore).storeArticle(article);

  }

  @Test
  void duplicateArticle() {

    Article article = articleStore.createArticle("someText", "title");
    articleStore.createCopy(article.getId());

    //2 times since the both articles have the same contents
    Mockito.verify(articleStore, times(2)).storeArticle(ArticleMatcher.isDuplicateOf(article));
  }

  @Test
  void checkStub() {

    Article article1 = new Article(1, "someText", "title");
    Article article2 = new Article(2, "someText", "title");
    Article article3 = new Article(3, "someText", "title");

    Mockito.when(articleStore.getArticlesLike(ArticleMatcher.isDuplicateOf(article2))).thenReturn(Arrays.asList(article1, article2));

    List<Article> articles = articleStore.getArticlesLike(article3);
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
  public static Article isDuplicateOf(Article article) {
    mockingProgress().getArgumentMatcherStorage().reportMatcher(new ArticleMatcher(article));
    return null;
  }

  public ArticleMatcher(Article article) {
    this.article = article;
  }

  /**
   * Implements matches method with our matching logic.
   *
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