package us.antenado.restnews.rest.dto;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by isilva on 10/21/17.
 */

public class FullNewsDTO {

    @Json(name = "_id")
    private String id;
    @Json(name = "updatedAt")
    private Date updatedAt;
    @Json(name = "createdAt")
    private Date createdAt;
    @Json(name = "title")
    private String title;
    @Json(name = "content")
    private String content;
    @Json(name = "__v")
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "FullNewsDTO{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", v=" + v +
                '}';
    }

}
