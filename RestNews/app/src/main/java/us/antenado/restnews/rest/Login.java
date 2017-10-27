package us.antenado.restnews.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import us.antenado.restnews.rest.dto.LoginDTO;
import us.antenado.restnews.rest.dto.LoginResultDTO;

/**
 * Created by isilva on 10/21/17.
 */

public interface Login {
    @POST("api/v1/login")
    Call<LoginResultDTO> login(@Body LoginDTO request);
}
