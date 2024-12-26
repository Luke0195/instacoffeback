package br.com.instacoffe.app.domain.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "spots")
public class Spot implements Serializable {

    @MongoId
    private String id;
    private String name;
    private String thumbnail;
    private Double price;
    private String[] techs;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;


    public Spot(){};

    public Spot(String id, String name, String thumbnail, Double price, String[] techs, Date createdAt, Date updatedAt){
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.techs = techs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String[] getTechs() {
        return techs;
    }

    public void setTechs(String[] techs) {
        this.techs = techs;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
