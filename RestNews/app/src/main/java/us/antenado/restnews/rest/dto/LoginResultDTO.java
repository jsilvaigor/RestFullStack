package us.antenado.restnews.rest.dto;

import com.squareup.moshi.Json;

/**
 * Created by isilva on 10/21/17.
 */

public class LoginResultDTO {
    @Json(name = "logged")
    private Boolean logged;

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "LoginResultDTO{" +
                "logged=" + logged +
                '}';
    }
}
