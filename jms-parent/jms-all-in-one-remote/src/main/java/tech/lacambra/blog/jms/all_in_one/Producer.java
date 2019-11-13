package tech.lacambra.blog.jms.all_in_one;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class Producer {

  @Inject
  @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory2")
//  @JMSPasswordCredential(userName = "admin", password = "admin")
  @JMSPasswordCredential(userName = "jms", password = "jms")
  JMSContext context;

  @Resource(lookup = "java:/jms/queue/DLQ")
  Queue queue;

  public void sendMessage(String message) {
    context.createProducer().send(queue, message);
  }

  public void sendMessage(Message message) {
    context.createProducer().send(queue, message);
  }

}
