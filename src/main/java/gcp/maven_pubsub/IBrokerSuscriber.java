package gcp.maven_pubsub;

public interface IBrokerSuscriber {

	/**
	 * Susbcribes to a Broker. 
	 * @throws Exception is subscription is ended
	 */
	public void subscribe(String subscriptionId) throws Exception;
}
