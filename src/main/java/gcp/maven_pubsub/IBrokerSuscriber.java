package gcp.maven_pubsub;

import java.util.concurrent.BlockingQueue;

public interface IBrokerSuscriber {

	/**
	 * Susbcribes to a Broker. 
	 * @param subscriptionId
	 * @param colaDeMensajes
	 * @throws Exception is subscription is ended
	 */
	public void subscribe(String subscriptionId,BlockingQueue<String> colaDeMensajes) throws Exception;
}
