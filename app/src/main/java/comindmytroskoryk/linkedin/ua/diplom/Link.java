package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Dem on 16.01.2017.
 */
public interface Link {
/*
    @FormUrlEncoded
    @POST("/api/login/")
    Call<User> authentication(@Field(value = "ClientUsers[email]") String email,
                              @Field(value = "ClientUsers[password]") String password,
                              @Field(value = "ClientUsers[group_ids]") String id_group);*/

    @FormUrlEncoded
    @POST("/maintain-api/login/")
    Call<User> authentication(@Field(value = "ClientUsers[email]") String email,
                              @Field(value = "ClientUsers[password]") String password);


    @GET("maintain-api/beacons?api_key=iAIgV_G58mdVpoH5mf3THMsm9A_A4dI7")
    Call<ArrayList<Unswer>> getGroups();;


    @GET("smart-city/categories")
    Call<ArrayList<Unswer>> getGroups2();

}
