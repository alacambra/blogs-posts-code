package tech.lacambra.blog.jms.all_in_one;

import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

public class Producer {

//  @Inject
//  @JMSConnectionFactory("java:jboss/exported/jms/RemoteConnectionFactory")
//  @JMSPasswordCredential(userName = "jms", password = "jms")
  JMSContext context;

//  @Resource(lookup = "java:global/jms/pointsQueue")
  Queue pointsQueue;

  public void sendMessage(String message) {
    context.createProducer().send(pointsQueue, message);
  }

  public void sendMessage(Message message) {
    context.createProducer().send(pointsQueue, message);
  }

}
