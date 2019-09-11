package tech.lacambra.blog.jms.all_in_one;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.logging.Logger;

@JMSDestinationDefinition(name = "java:global/jms/pointsQueue", interfaceName = "javax.jms.Queue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/DLQ"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:jboss/exported/jms/RemoteConnectionFactory"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "user", propertyValue = "jms"),
    @ActivationConfigProperty(propertyName = "password", propertyValue = "jms"),
//    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "type = 'T'"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
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