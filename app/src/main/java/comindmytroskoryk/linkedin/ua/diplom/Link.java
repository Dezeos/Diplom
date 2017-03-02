package comindmytroskoryk.linkedin.ua.diplom;



import java.util.ArrayList;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Url;

/*
Интерфейс реализует формирование запросов на сервер
 */
public interface Link {


    /*
    POST запрос для получения API KEYя пользователя
     */
    @FormUrlEncoded
    @POST("/maintain-api/login")
    Call<User> authentication(@Field(value = "Users[email]") String email,
                              @Field(value = "Users[password]") String password);


    /*
    POST запрос для отправки контента на сервер, с последующим сохранением
     */
    @FormUrlEncoded
    @POST
    Call<StatusResult> redact (@Url String endpoint,
                               @Field(value = "Beacons[title]") String title,
                               @Field(value = "Beacons[description]") String description);

    /*
    GET запрос для получение всех полей всех групп,
    с последующей выборкой необходимого
     */
    @GET
    Call<ArrayList<Unswer>> getAllAboutGroups(@Url String endpoint);;

    /*
    GET запрос для получения краткой информации о каждой группе
     */
    @GET("smart-city/categories")
    Call<ArrayList<Unswer>> getShortAboutGroups();

}
