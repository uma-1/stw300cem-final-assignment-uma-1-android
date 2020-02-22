package reusable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reusable {
   public static final String Base_URL = "http://10.0.2.2:6000/";
   public static String Cookie ="";

    public static Retrofit getInstance() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Reusable.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }


}
