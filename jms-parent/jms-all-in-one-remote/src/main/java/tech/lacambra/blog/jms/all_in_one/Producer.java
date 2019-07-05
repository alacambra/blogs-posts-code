package tech.lacambra.blog.jms.all_in_one;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class Producer {

  @Inject
  @JMSConnectionFactory("java:jboss/exported/jms/RemoteConnectionFactory")
  @JMSPasswordCredential(userName = "jms", password = "jms")
  JMSContext context;

  @Resource(lookup = "java:global/jms/pointsQueue")
  Queue pointsQueue;

  public void sendMessage(String message) {
    context.createProducer().send(pointsQueue, message);
  }

  public void sendMessage(Message message) {
    context.createProducer().send(pointsQueue, message);
  }

}
