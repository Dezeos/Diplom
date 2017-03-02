package comindmytroskoryk.linkedin.ua.diplom;



import java.util.ArrayList;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Url;

/**
 * Created by Dem on 16.01.2017.
 */
public interface Link {



    @FormUrlEncoded
    @POST("/maintain-api/login")
    Call<User> authentication(@Field(value = "Users[email]") String email,
                              @Field(value = "Users[password]") String password);

    @FormUrlEncoded
    @POST
    Call<redactSTATUS> redact (@Url String endpoint,
                       @Field(value = "Beacons[title]") String title,
                       @Field(value = "Beacons[description]") String description);


    @GET
    Call<ArrayList<Unswer>> getAllAboutGroups(@Url String endpoint);;


    @GET("smart-city/categories")
    Call<ArrayList<Unswer>> getShortAboutGroups();

}
