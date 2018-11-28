package airbnb.api;

import airbnb.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.List;

public class Requester {

    private static String userServiceUrl = "http://127.0.0.1:8081/user";

    public static List<User> getAllUsers() {
        Client client = Client.create();
        WebResource webResource = client.resource(userServiceUrl);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        String result = response.getEntity(String.class);
        return new Gson().fromJson(result, new TypeToken<List<User>>(){}.getType());
    }

    public static User getUserByEmail() {
        return null;
    }

    public static List<User> getUsersByNameSurname() {
        return null;
    }

    public static boolean updateUser(User user) {
        return false;
    }

    public static User createOrUpdateUser(User user) {
        return null;
    }

    public static boolean deleteUserByEmial(String email) {
        return false;
    }

    public static boolean deleteUser(User user) {
        return false;
    }
}
