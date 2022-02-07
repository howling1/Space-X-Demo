package edu.tum.backend.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ships")
public class Ship {
    @Id
    private String id;

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", year_built=" + year_built +
                '}';
    }

    @NonNull
    @Indexed(unique = true)
    private String name;

    private String link;

    Integer year_built;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setYear_built(Integer year_built) {
        this.year_built = year_built;
    }

    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public Integer getYear_built() {
        return year_built;
    }

    public String getLink() {
        return link;
    }
}
