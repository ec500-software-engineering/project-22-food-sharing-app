package com.example.helislaptop.foodsharing.foodList;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "foodItem")
public class FoodItem implements Parcelable {




    @PrimaryKey
    @NonNull
    public int itemId;

    public String owner;
    public int capacity;
    public String description;
    public String image;
    public String time;
    public String postOrRequest;

    protected FoodItem(Parcel in) {
        itemId = in.readInt();
        owner = in.readString();
        capacity = in.readInt();
        description = in.readString();
        image = in.readString();
        time = in.readString();
        postOrRequest = in.readString();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemId);
        dest.writeString(owner);
        dest.writeInt(capacity);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(time);
        dest.writeString(postOrRequest);
    }
}
