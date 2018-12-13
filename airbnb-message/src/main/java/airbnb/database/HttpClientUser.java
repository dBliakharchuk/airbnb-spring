package airbnb.database;

import java.lang.reflect.Type;
import java.util.List;

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



import airbnb.model.User;
import airbnb.model.Message; 


public class HttpClientUser {
	public static final Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
            new ByteArrayToBase64TypeAdapter()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    private static String userServiceUrl = "http://127.0.0.1:8081/user";

    public static List<User> getAllUsers() {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);
        
        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<User>>(){}.getType());
    }

    public static User getUserByEmail(String email) {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .queryParam("email", email)
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, User.class);
    }

    public static List<User> getUsersByNameSurname(String name, String surname) {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .queryParam("name", name)
                .queryParam("surname", surname)
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, new TypeToken<List<User>>(){}.getType());
    }

    public static boolean updateUser(User user) {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .post(ClientResponse.class, customGson.toJson(user));

        String result = response.getEntity(String.class);
        return Boolean.valueOf(result);
    }

    public static User createOrUpdateUser(User user) {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .put(ClientResponse.class, customGson.toJson(user));

        String result = response.getEntity(String.class);
        return customGson.fromJson(result, User.class);
    }

    public static boolean deleteUserByEmial(String email) {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .queryParam("email", email)
                .accept("application/json")
                .type("application/json")
                .delete(ClientResponse.class);

        String result = response.getEntity(String.class);
        return Boolean.valueOf(result);
    }

    public static boolean deleteUser(User user) {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .delete(ClientResponse.class, customGson.toJson(user));

        String result = response.getEntity(String.class);
        return Boolean.valueOf(result);
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
