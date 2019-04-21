package gcp.maven_pubsub;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.google.pubsub.v1.PubsubMessage;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		// New suscriber connected to GCP
		IBrokerSuscriber iBrokerSuscriber = new GCPSuscriber();

		BlockingQueue<String> colaDeMensajes = new LinkedBlockingDeque<>();

		// Suscribe to the topic
		iBrokerSuscriber.subscribe("suscri_test", colaDeMensajes);

		// Continue to listen to messages
		while (true) {
			String message = colaDeMensajes.take();
			System.out.println("Mensaje tratado: " + message);

		}
	}
}
