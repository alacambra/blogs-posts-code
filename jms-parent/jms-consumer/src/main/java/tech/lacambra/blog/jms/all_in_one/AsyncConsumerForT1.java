package tech.lacambra.blog.jms.all_in_one;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/pointsQueue"),
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "type = 'T'"),
//    @ActivationConfigProperty(propertyName = "connectionParameters", propertyValue = "host=localhost;port=5445"),
//    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})

public class AsyncConsumerForT1 implements MessageListener {

  private static final Logger LOGGER = Logger.getLogger(AsyncConsumerForT1.class.toString());

  /**
   * @see MessageListener#onMessage(Message)
   */
  public void onMessage(Message rcvMessage) {

    TextMessage msg = null;
    try {
      if (rcvMessage instanceof TextMessage) {
        msg = (TextMessage) rcvMessage;
        LOGGER.info("T1: Received Message from queue: " + msg.getText());
      } else {
        LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
      }
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
  }
}