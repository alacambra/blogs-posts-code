package tech.lacambra.blog.jms.all_in_one;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.jms.*;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Singleton
//@Startup
@JMSDestinationDefinition(name = "java:global/jms/pointsQueue", interfaceName = "javax.jms.Queue")
public class Sender {

  private static final Logger LOGGER = Logger.getLogger(Sender.class.toString());

  @Inject
  Instance<Producer> producers;

  @Inject
  @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory2")
//  @JMSPasswordCredential(userName = "jms", password = "jms")
  JMSContext context;

  @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
  public void send() throws JMSException {

    Message message = context.createTextMessage("a message " + LocalDateTime.now());
    message.setStringProperty("type", "T");
    LOGGER.info("Sender: Sending message: " + ((TextMessage) message).getText());

    producers.get().sendMessage(message);
  }
}
