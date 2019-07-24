package tech.lacambla.blog.security;

import org.jboss.security.SecurityContext;
import org.jboss.security.auth.callback.UsernamePasswordHandler;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;

import static org.jboss.security.SecurityContextFactory.createSecurityContext;

@Singleton
@Startup
public class Authenticator {

  public Authenticator() {
    try {

      LoginContext ctx = new LoginContext("MultiSourceDomain", new UsernamePasswordHandler("u1", "p1"));

      ctx.login();

      for (Principal principal : ctx.getSubject().getPrincipals()) {
        System.out.println(principal.getName() + " : ");
      }

      SecurityContext sc = createSecurityContext("MultiSourceDomain");
      sc.getUtil().createSubjectInfo(ctx.getSubject().getPrincipals().iterator().next(), "", ctx.getSubject());

      System.out.println(sc);
    } catch (LoginException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
