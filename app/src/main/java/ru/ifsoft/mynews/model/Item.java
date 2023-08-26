package ru.ifsoft.mynews.model;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

import ru.ifsoft.mynews.constants.Constants;


public class Item extends Application implements Constants, Parcelable {

    private long id, fromUserId;
    private int createAt, likesCount, commentsCount, allowComments, categoryId;
    private String timeAgo, date, categoryTitle, itemTitle, itemDescription, itemContent, imgUrl, fromUserUsername, fromUserFullname, fromUserPhotoUrl, area, country, city;
    private Double lat = 0.000000, lng = 0.000000;
    private Boolean myLike;

    public Item() {

    }

    public Item(JSONObject jsonData) {

        try {

            if (!jsonData.getBoolean("error")) {

                this.setId(jsonData.getLong("id"));
                this.setFromUserId(jsonData.getLong("fromUserId"));
                this.setFromUserUsername(jsonData.getString("fromUserUsername"));
                this.setFromUserFullname(jsonData.getString("fromUserFullname"));
                this.setFromUserPhotoUrl(jsonData.getString("fromUserPhoto"));
                this.setContent(jsonData.getString("itemContent"));
                this.setTitle(jsonData.getString("itemTitle"));
                this.setDescription(jsonData.getString("itemDesc"));
                this.setCategoryTitle(jsonData.getString("categoryTitle"));
                this.setCategoryId(jsonData.getInt("category"));
                this.setImgUrl(jsonData.getString("imgUrl"));
                this.setArea(jsonData.getString("area"));
                this.setCountry(jsonData.getString("country"));
                this.setCity(jsonData.getString("city"));
                this.setAllowComments(jsonData.getInt("allowComments"));
                this.setCommentsCount(jsonData.getInt("commentsCount"));
                this.setLikesCount(jsonData.getInt("likesCount"));
                this.setMyLike(jsonData.getBoolean("myLike"));
                this.setLat(jsonData.getDouble("lat"));
                this.setLng(jsonData.getDouble("lng"));
                this.setCreateAt(jsonData.getInt("createAt"));
                this.setDate(jsonData.getString("date"));
                this.setTimeAgo(jsonData.getString("timeAgo"));

            }

        } catch (Throwable t) {

            Log.e("Item", "Could not parse malformed JSON: \"" + jsonData.toString() + "\"");

        } finally {

            Log.d("Item", jsonData.toString());
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromUserId() {

        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {

        this.fromUserId = fromUserId;
    }

    public int getAllowComments() {

        return allowComments;
    }

    public void setAllowComments(int allowComments) {
        this.allowComments = allowComments;
    }


    public int getCommentsCount() {

        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getLikesCount() {

        return this.likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCategoryId() {

        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {

        this.categoryId = categoryId;
    }

    public int getCreateAt() {

        return createAt;
    }

    public void setCreateAt(int createAt) {
        this.createAt = createAt;
    }

    public String getTimeAgo() {

        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {

        this.timeAgo = timeAgo;
    }


    public String getContent() {

        return itemContent;
    }

    public void setContent(String itemContent) {

        this.itemContent = itemContent;
    }

    public String getTitle() {

        return itemTitle;
    }

    public void setTitle(String itemTitle) {

        this.itemTitle = itemTitle;
    }

    public String getDescription() {

        return itemDescription;
    }

    public void setDescription(String itemDescription) {

        this.itemDescription = itemDescription;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }


    public String getFromUserUsername() {
        return fromUserUsername;
    }

    public void setFromUserUsername(String fromUserUsername) {
        this.fromUserUsername = fromUserUsername;
    }


    public String getFromUserFullname() {
        return fromUserFullname;
    }

    public void setFromUserFullname(String fromUserFullname) {
        this.fromUserFullname = fromUserFullname;
    }


    public String getFromUserPhotoUrl() {

        if (fromUserPhotoUrl == null) {

            fromUserPhotoUrl = "";
        }

        return fromUserPhotoUrl;
    }

    public void setFromUserPhotoUrl(String fromUserPhotoUrl) {
        this.fromUserPhotoUrl = fromUserPhotoUrl;
    }


    public Boolean isMyLike() {
        return myLike;
    }

    public void setMyLike(Boolean myLike) {

        this.myLike = myLike;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {

        if (this.area == null) {

            this.area = "";
        }

        return this.area;
    }

    public void setArea(String area) {

        this.area = area;
    }

    public String getCountry() {

        if (this.country == null) {

            this.country = "";
        }

        return this.country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getCity() {

        if (this.city == null) {

            this.city = "";
        }

        return this.city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public Double getLat() {

        return this.lat;
    }

    public void setLat(Double lat) {

        this.lat = lat;
    }

    public Double getLng() {

        return this.lng;
    }

    public void setLng(Double lng) {

        this.lng = lng;
    }

    public String getLink() {

        return WEB_SITE + this.getFromUserUsername() + "/post/" + Long.toString(this.getId());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Item createFromParcel(Parcel in) {

            return new Item();
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
