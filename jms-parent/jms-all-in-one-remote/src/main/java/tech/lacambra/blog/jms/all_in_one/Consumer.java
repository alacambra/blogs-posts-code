package tech.lacambra.blog.jms.all_in_one;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class Consumer {

  @Inject
  @JMSConnectionFactory("java:jboss/exported/jms/RemoteConnectionFactory")
  @JMSPasswordCredential(userName = "jms", password = "jms")
  JMSContext context;

  @Resource(lookup = "java:global/jms/pointsQueue")
  Queue pointsQueue;

  @PostConstruct
  public void init() throws JMSException {
    System.out.println("Starting received");
    receiveMessage();
  }

  public String receiveMessage() {
    try (JMSConsumer consumer = context.createConsumer(pointsQueue)) {
      String message = consumer.receiveBody(String.class);
      System.out.println("Received message: " + message);
      return message;
    }
  }

  public int getQueueSize() {
    int count = 0;
    try {
      QueueBrowser browser = context.createBrowser(pointsQueue);
      Enumeration elems = browser.getEnumeration();
      while (elems.hasMoreElements()) {
        elems.nextElement();
        count++;
      }
    } catch (JMSException ex) {
      Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("Getting queue size: " + count);
    return count;
  }

}