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



    /*
    @FormUrlEncoded
    @POST("/api/login/")
    Call<User> authentication(@Field(value = "ClientUsers[email]") String email,
                              @Field(value = "ClientUsers[password]") String password,
                              @Field(value = "ClientUsers[group_ids]") String id_group);


    */

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
    Call<ArrayList<Unswer>> getGroups(@Url String endpoint);;


    @GET("smart-city/categories")
    Call<ArrayList<Unswer>> getGroups2();

}
