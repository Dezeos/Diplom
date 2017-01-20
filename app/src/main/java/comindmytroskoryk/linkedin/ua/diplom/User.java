package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dem on 16.01.2017.
 */
public class User {




    @SerializedName("api_key")
    private String api_key;


    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    @Override
    public String toString() {
        return "User{" +
                "api_key='" + api_key + '\'' +
                '}';
    }

}
