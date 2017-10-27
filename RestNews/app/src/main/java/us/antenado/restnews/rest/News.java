package us.antenado.restnews.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import us.antenado.restnews.rest.dto.FullNewsDTO;
import us.antenado.restnews.rest.dto.SimpleNewsDTO;

/**
 * Created by isilva on 10/21/17.
 */

public interface News {
    @GET("api/v1/news")
    Call<List<SimpleNewsDTO>> getAll();

    @POST("api/v1/news")
    Call<Void> createNews(@Body FullNewsDTO newsDTO);

    @GET("api/v1/news/{id}")
    Call<FullNewsDTO> getSpecific(@Path("id") String id);

    @DELETE("api/v1/news/{id}")
    Call<Void> deleteNews(@Path("id") String id);

    @PUT("api/v1/news/{id}")
    Call<Void> updateNews(@Path("id") String id, @Body FullNewsDTO fullNewsDTO);
}
