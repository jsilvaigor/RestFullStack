package us.antenado.restnews.rest.dto;

import com.squareup.moshi.Json;

/**
 * Created by isilva on 10/21/17.
 */

public class SimpleNewsDTO {

    @Json(name = "_id")
    private String id;
    @Json(name = "title")
    private String title;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SimpleNewsDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
