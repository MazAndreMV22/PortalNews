package ru.ifsoft.mynews.model;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

import ru.ifsoft.mynews.constants.Constants;

public class Category extends Application implements Constants, Parcelable {

    private long id;
    private int itemsCount, createAt;
    private String title, description, imgUrl;

    public Category() {

    }

    public Category(JSONObject jsonData) {

        try {

            this.setId(jsonData.getLong("id"));
            this.setItemsCount(jsonData.getInt("itemsCount"));
            this.setImgUrl(jsonData.getString("imgUrl"));
            this.setTitle(jsonData.getString("title"));
            this.setDescription(jsonData.getString("description"));
            this.setCreateAt(jsonData.getInt("createAt"));

        } catch (Throwable t) {

            Log.e("Category", "Could not parse malformed JSON: \"" + jsonData.toString() + "\"");

        } finally {

            Log.d("Category", jsonData.toString());
        }
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return this.id;
    }


    public void setItemsCount(int itemsCount) {

        this.itemsCount = itemsCount;
    }

    public int getItemsCount() {

        return this.itemsCount;
    }


    public void setTitle(String title) {

        this.title = title;
    }

    public String getTitle() {

        return this.title;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getDescription() {

        return this.description;
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {

        return this.imgUrl;
    }

    public void setCreateAt(int createAt) {

        this.createAt = createAt;
    }

    public int getCreateAt() {

        return this.createAt;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    public static final Creator CREATOR = new Creator() {

        public Category createFromParcel(Parcel in) {

            return new Category();
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
