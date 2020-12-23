package ru.job4j.url.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "encoded_url")
public class EncodedUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String code;
    private int followings;

    public EncodedUrl() {
    }

    public EncodedUrl(String url) {
        this.url =  url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFollowings() {
        return followings;
    }

    public void setFollowings(int followings) {
        this.followings = followings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncodedUrl that = (EncodedUrl) o;
        return id == that.id &&
                followings == that.followings &&
                Objects.equals(url, that.url) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, code, followings);
    }
}
