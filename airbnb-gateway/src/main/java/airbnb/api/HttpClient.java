package airbnb.api;

import airbnb.model.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.Base64;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.lang.reflect.Type;
import java.util.List;

public class HttpClient {

    public static final Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
            new ByteArrayToBase64TypeAdapter()).create();

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
