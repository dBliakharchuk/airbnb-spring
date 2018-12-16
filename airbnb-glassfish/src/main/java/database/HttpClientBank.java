package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.PaymentInfo;

public class HttpClientBank {
	public static final Gson customGson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
    private static String userServiceUrl = "http://127.0.0.1:8083/bank";

    public static boolean isPaymentInfoValid(PaymentInfo info) {
    	String result = null;
        try {
        	Client client = Client.create();
            WebResource webResource = client.resource(userServiceUrl);
            ClientResponse response = webResource
                    .accept("application/json")
                    .type("application/json")
                    .post(ClientResponse.class, customGson.toJson(info));

            result = response.getEntity(String.class);
	
        } catch (ClientHandlerException ex) {
        	System.out.println("HttpClientUser exception: there was problem with connection");
        	return false;
        }
    	
        return Boolean.valueOf(result);
    }

}
