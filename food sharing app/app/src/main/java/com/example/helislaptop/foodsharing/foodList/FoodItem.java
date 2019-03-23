package com.example.helislaptop.foodsharing.foodList;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "foodItem")
public class FoodItem{




    @PrimaryKey
    @NonNull
    public int itemId;

    public String owner;
    public int capacity;
    public String description;
    public String image;
    public String time;
    public String postOrRequest;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCapacity(@NonNull int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @NonNull
    public int getItemId() {
        return itemId;
    }

    public void setItemId(@NonNull int itemId) {
        this.itemId = itemId;
    }

    public void setPostOrRequest(String postOrRequest) {
        this.postOrRequest = postOrRequest;
    }

    public String getOwner() {
        return owner;
    }


    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

    public String getPostOrRequest() {
        return postOrRequest;
    }
    public FoodItem() {

    }


}
