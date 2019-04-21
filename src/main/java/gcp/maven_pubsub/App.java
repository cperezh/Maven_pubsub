package gcp.maven_pubsub;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	//New suscriber connected to GCP
    	IBrokerSuscriber iBrokerSuscriber = new GCPSuscriber();
    	
    	//Suscribe to the topic
    	iBrokerSuscriber.subscribe("suscri_test");
    }
}
