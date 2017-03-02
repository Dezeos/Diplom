package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dem on 16.01.2017.
 */
public class User {

    @SerializedName("api_key")
    private String apiKey;

    public String getApiKey() {

        return apiKey;
    }

    public void setApiKey(String api_key) {

        this.apiKey = api_key;

    }

    @Override
    public String toString() {
        return "User{" +
                "apiKey='" + apiKey + '\'' +
                '}';
    }

}
