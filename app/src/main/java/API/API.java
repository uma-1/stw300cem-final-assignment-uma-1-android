package API;

import java.util.List;

import model.ImageResponse;
import model.Items;
import model.LoginSignupResponse;
import model.Users;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface API {

    @POST("user/login")
    Call<LoginSignupResponse> loginUser( @Body Users user);
    @POST("item")
    Call<Void> addItem(@Body Items item);


    @POST("user")
    Call<Void> insertUser(@Body Users user);

    @Multipart
    @POST("item/upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("item")
    Call<List<Items>> getAllItems();

    @GET("api/search/item/{itemName}")
    Call<List<Items>> searchitem(@Path("itemName") String itemName);




//    @POST("user/login")
//    Call<LoginSignupResponse> loginUser(String username, String password);



    @GET("api/search/item/id/{itemId}")
    Call<Items> getItemById(@Path("itemId") int itemId);


    @FormUrlEncoded
    @PUT("api/update/{itemId}")
    Call<Void> updateItem(
        @Path("itemId") int itemId,
        @Field("name") String name,
        @Field("module") String module,
        @Field("price") String price
    );


    @DELETE("item/del/{itemId}")
    Call<Void> deleteItem(@Path("itemId") int itemId);
}

