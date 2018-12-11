package database;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Apartment;
import model.ApartmentPK;
import model.User;

public class HttpClientApartment {
	public static final Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
            new ByteArrayToBase64TypeAdapter()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    private static String apartmentServiceUrl = "http://127.0.0.1:8083/apartment";
    
    /*Host in apartment = null but we can get email of host from ApartmentPK*/
    public static List<Apartment> getAllApartments() {
        Client client = Client.create();
        WebResource webResource = client.resource(apartmentServiceUrl);
        ClientResponse response = webResource
        		.accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);
        
        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    
    public static Apartment getApartmentById(ApartmentPK apartmentId) {
    	Client client = Client.create();
        WebResource webResource = client.resource(apartmentServiceUrl + "/getById");
        ClientResponse response = webResource
        		.accept("application/json")
                .type("application/json")
                .post(ClientResponse.class, customGson.toJson(apartmentId));

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, Apartment.class);
    }
    
    public static List<Apartment> getApartmentByName(String name) {
        Client client = Client.create();
        WebResource webResource = client.resource(apartmentServiceUrl);
        ClientResponse response = webResource
                .queryParam("name", name)
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static List<Apartment> getApartmentByCity(String city) {
        Client client = Client.create();
        WebResource webResource = client.resource(apartmentServiceUrl);
        ClientResponse response = webResource
                .queryParam("city", city)
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static List<Apartment> getApartmentByHost(String host) {
        Client client = Client.create();
        WebResource webResource = client.resource(apartmentServiceUrl);
        ClientResponse response = webResource
                .queryParam("host", host)
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static List<Apartment> getApartmentByCounry(String country) {
        Client client = Client.create();
        WebResource webResource = client.resource(apartmentServiceUrl);
        ClientResponse response = webResource
                .queryParam("country", country)
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<Apartment>>(){}.getType());
    }
    
    public static boolean createApartment(Apartment apartment) {
    	Client client = Client.create();
    	WebResource webResource = client.resource(apartmentServiceUrl);
    	ClientResponse response = webResource
    			.accept("application/json")
    			.type("application/json")
    			.post(ClientResponse.class, customGson.toJson(apartment));
    	
    	String result = response.getEntity(String.class);
    	return customGson.fromJson(result, Boolean.class);
    }
    
    public static boolean updateApartment(Apartment apartment) {
    	Client client = Client.create();
    	WebResource webResource = client.resource(apartmentServiceUrl);
    	ClientResponse response = webResource
    			.accept("application/json")
    			.type("application/json")
    			.put(ClientResponse.class, customGson.toJson(apartment));
    	
    	String result = response.getEntity(String.class);
    	return customGson.fromJson(result, Boolean.class);
    }
    
    public static boolean removeApartment(Apartment apartment) {
    	Client client = Client.create();
    	WebResource webResource = client.resource(apartmentServiceUrl);
    	ClientResponse response = webResource
    			.accept("application/json")
    			.type("application/json")
    			.delete(ClientResponse.class, customGson.toJson(apartment));
    	
    	String result = response.getEntity(String.class);
    	return customGson.fromJson(result, Boolean.class);
    }
        
    
    
    public static void main(String []args) {
    	
    	System.out.println("List of apartment***************************");
    	List<Apartment> listAp = getAllApartments();
    	Apartment newAp = listAp.get(6);
    	ApartmentPK idAp = newAp.getId();
    	
    	
    	listAp = getAllApartments();
    	for(Apartment ap:listAp) {
    		 System.out.println(ap);
    		 System.out.println("------------------------------------");
    	 }
    	System.out.println("*****************get Apartment by id");
    	System.out.println(getApartmentById(idAp));
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
