package database;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.Base64;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Apartment;
import model.ApartmentPK;
import model.User;

public class HttpClientApartment {
	public static final Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
            new ByteArrayToBase64TypeAdapter()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    private static String apartmentServiceUrl = "http://127.0.0.1:8084/apartment";
    
    public static List<Apartment> getAllApartments() {
    	String result = null;
    	try {
	    	Client client = Client.create();
	        WebResource webResource = client.resource(apartmentServiceUrl);
	        ClientResponse response = webResource
	        		.accept("application/json")
	                .type("application/json")
	                .get(ClientResponse.class);
	        
	        result = response.getEntity(String.class);
	    } catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return new ArrayList<Apartment>();
	    }
    	return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    
    public static Apartment getApartmentById(ApartmentPK apartmentId) {
    	String result = null;
    	
    	try {
    		Client client = Client.create();
	        WebResource webResource = client.resource(apartmentServiceUrl + "/getById");
	        ClientResponse response = webResource
	        		.accept("application/json")
	                .type("application/json")
	                .post(ClientResponse.class, customGson.toJson(apartmentId));
	
	        result = response.getEntity(String.class);
    	} catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return null;
	    }
	    	
        return customGson.fromJson(result, Apartment.class);
    }
    
    public static List<Apartment> getApartmentByName(String name) {
        String result = null;
    	try {
	    	Client client = Client.create();
	        WebResource webResource = client.resource(apartmentServiceUrl);
	        ClientResponse response = webResource
	                .queryParam("name", name)
	                .accept("application/json")
	                .type("application/json")
	                .get(ClientResponse.class);

	        result = response.getEntity(String.class);
    	} catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return new ArrayList<Apartment>();
	    }
    	
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static List<Apartment> getApartmentByCity(String city) {
        String result = null;
        try {
	    	Client client = Client.create();
	        WebResource webResource = client.resource(apartmentServiceUrl);
	        ClientResponse response = webResource
	                .queryParam("city", city)
	                .accept("application/json")
	                .type("application/json")
	                .get(ClientResponse.class);
	        result = response.getEntity(String.class);
        } catch (ClientHandlerException ex) {
		    	System.out.println("HttpClientApartment exception: there was problem with connection");
		    	return new ArrayList<Apartment>();
		  }
        
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static List<Apartment> getApartmentByHost(String host) {
        String result = null;
        
        try {
	    	Client client = Client.create();
	        WebResource webResource = client.resource(apartmentServiceUrl);
	        ClientResponse response = webResource
	                .queryParam("host", host)
	                .accept("application/json")
	                .type("application/json")
	                .get(ClientResponse.class);
	        result = response.getEntity(String.class);
        } catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return new ArrayList<Apartment>();
        }

        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static List<Apartment> getApartmentByCounry(String country) {
        String result = null;
        try {
	    	Client client = Client.create();
	        WebResource webResource = client.resource(apartmentServiceUrl);
	        ClientResponse response = webResource
	                .queryParam("country", country)
	                .accept("application/json")
	                .type("application/json")
	                .get(ClientResponse.class);
	        
	        result = response.getEntity(String.class);
        } catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return new ArrayList<Apartment>();
        }
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static boolean createApartment(Apartment apartment) {    	
    	/*apartment.setPicture(null);*/
    	String result = null;
    	try {
	    	Client client = Client.create();
	    	WebResource webResource = client.resource(apartmentServiceUrl);
	    	ClientResponse response = webResource
	    			.accept("application/json")
	    			.type("application/json")
	    			.post(ClientResponse.class, customGson.toJson(apartment));
	    	
	    	result = response.getEntity(String.class);
    	} catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return false;
        }
    	return customGson.fromJson(result, Boolean.class);
    }
    
    public static boolean updateApartment(Apartment apartment) {
    	/*apartment.setPicture(null);*/
    	String result = null;
    	try { 
	    	Client client = Client.create();
	    	WebResource webResource = client.resource(apartmentServiceUrl);
	    	ClientResponse response = webResource
	    			.accept("application/json")
	    			.type("application/json")
	    			.put(ClientResponse.class, customGson.toJson(apartment));
	    	
	    	result = response.getEntity(String.class);
    	} catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return false;
        }
    	return customGson.fromJson(result, Boolean.class);
    }
    
    public static boolean removeApartment(Apartment apartment) {
    	String result = null;
    	
    	try {
	    	apartment.setPicture(null);
	    	Client client = Client.create();
	    	WebResource webResource = client.resource(apartmentServiceUrl);
	    	ClientResponse response = webResource
	    			.accept("application/json")
	    			.type("application/json")
	    			.delete(ClientResponse.class, customGson.toJson(apartment));
	    	
	    	result = response.getEntity(String.class);
    	} catch (ClientHandlerException ex) {
	    	System.out.println("HttpClientApartment exception: there was problem with connection");
	    	return false;
        }
    	return customGson.fromJson(result, Boolean.class);
    }
        

  

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.decode(json.getAsString(), Base64.NO_WRAP);
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
        }
    }

}
