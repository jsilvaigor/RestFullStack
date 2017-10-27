package us.antenado.restnews.rest;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.Rfc3339DateJsonAdapter;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by isilva on 10/21/17.
 */

public class Rest {

    private static final String BASE_URL = "http://192.168.0.101:3000/";

    private static Retrofit retrofit;

    private Rest() {

        Moshi moshi = new Moshi.Builder()
                .add(Date.class, new Rfc3339DateJsonAdapter().nullSafe())
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build();
    }

    private static Retrofit getInstance() {

        if (retrofit != null) {
            return retrofit;
        } else {
            new Rest();
            return retrofit;
        }

    }

    public static Login loginService() {
        return getInstance().create(Login.class);
    }

    public static News newsService() {
        return getInstance().create(News.class);
    }
}
