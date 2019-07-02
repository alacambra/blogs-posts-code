package tech.lacambra.blog.jms.all_in_one;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.jms.*;
import java.time.LocalDateTime;

@Singleton
@Startup
@JMSDestinationDefinition(name = "java:global/jms/pointsQueue", interfaceName = "javax.jms.Queue")
public class Sender {

  @Inject
  Instance<Producer> producers;

  @Inject
  @JMSConnectionFactory("java:/ConnectionFactory")
  JMSContext context;

  @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
  public void send() throws JMSException {

    Message message = context.createTextMessage("a message " + LocalDateTime.now());
    message.setStringProperty("type", "T");
    producers.get().sendMessage(message);
  }
}
