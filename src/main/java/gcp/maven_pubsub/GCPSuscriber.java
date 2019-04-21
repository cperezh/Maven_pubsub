package gcp.maven_pubsub;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

/**
 * Subscriptor al PUB/SUB de GCPs
 * @author Carlos
 *
 */
public class GCPSuscriber implements IBrokerSuscriber {

	// use the default project id
	private final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	// La cola de mensajes
	private final BlockingQueue<PubsubMessage> messages = new LinkedBlockingDeque<>();

	public void subscribe(String subscriptionId) throws Exception {

		ProjectSubscriptionName subscriptionName;
		Subscriber subscriber = null;

		try {
			// Create the Subscription name
			subscriptionName = ProjectSubscriptionName.of(PROJECT_ID, subscriptionId);
			// Create the subscriber
			subscriber = Subscriber.newBuilder(subscriptionName, new MyMessageReciever()).build();

			// Lanzamos el subscriptor para que le envie mensajes a esta clase, en el metodo
			// receiveMessage
			subscriber.startAsync().awaitRunning();

			// Continue to listen to messages
			while (true) {
				PubsubMessage message = messages.take();
				System.out.println("Message Id: " + message.getMessageId());
				System.out.println("Data: " + message.getData().toStringUtf8());
				System.out.println("Attributes: "+message.getAttributesMap());

			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (subscriber != null) {
				subscriber.stopAsync();
			}
		}

	}

	/**
	 * Clase anidada que implementa el receptor de mensajes del topic. Los dejara en el objeto messages
	 * de la clase principal
	 * @author Carlos
	 *
	 */
	private class MyMessageReciever implements MessageReceiver{

		@Override
		public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
			messages.offer(message);
			consumer.ack();
			
		}
		
	}
}
