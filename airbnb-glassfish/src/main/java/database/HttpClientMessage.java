package database;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Message;
import model.User;

public class HttpClientMessage
{
	public static final Gson customGson = new GsonBuilder()
			.registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

	private static String messageServiceUrl = "http://127.0.0.1:8082/messages";

	public static List<Message> getNewestMessagesByEmail(String email)
	{

		String result = null;
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource(messageServiceUrl);
			ClientResponse response = webResource.queryParam("email", email).accept("application/json")
					.type("application/json").get(ClientResponse.class);

			result = response.getEntity(String.class);
		} catch (ClientHandlerException ex)
		{
			System.out.println("HttpClientMessage exception: there was problem with connection");
			return new ArrayList<>();
		}
		return customGson.fromJson(result, new TypeToken<List<Message>>()
		{}.getType());
	}

	public static List<Message> getConversation(String email, String selectedUser)
	{

		String result = null;
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource(messageServiceUrl);
			ClientResponse response = webResource.queryParam("email", email).queryParam("selectedUser", selectedUser)
					.accept("application/json").type("application/json").get(ClientResponse.class);

			result = response.getEntity(String.class);
		} catch (ClientHandlerException ex)
		{
			System.out.println("HttpClientMessage  exception: there was problem with connection");
			return new ArrayList<>();
		}

		return customGson.fromJson(result, new TypeToken<List<Message>>()
		{}.getType());

	}

	public static Message createMessage(Message message)
	{

		Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
				.disableInnerClassSerialization().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

		String result = null;
		try
		{

			Client client = Client.create();
			WebResource webResource = client.resource(messageServiceUrl);
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.put(ClientResponse.class, gson.toJson(message));

			result = response.getEntity(String.class);

		} catch (ClientHandlerException ex)
		{
			System.out.println("HttpClientMessage  exception: there was problem with connection");
			return null;
		}
		
		
		return customGson.fromJson(result, Message.class);
	}

	private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]>
	{
		public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException
		{
			return Base64.decode(json.getAsString(), Base64.NO_WRAP);
		}

		public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context)
		{
			return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
		}
	}

}
