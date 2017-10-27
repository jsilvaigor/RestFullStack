package us.antenado.restnews.rest.dto;

import com.squareup.moshi.Json;

/**
 * Created by isilva on 10/21/17.
 */

public class LoginDTO {

    @Json(name = "email")
    private String email;
    @Json(name = "password")
    private String password;

    public LoginDTO() {

    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
