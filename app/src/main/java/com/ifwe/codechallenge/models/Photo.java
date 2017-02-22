package com.ifwe.codechallenge.models;

/**
 * Created by sirig on 2/21/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "owner",
        "secret",
        "server",
        "farm",
        "title",
        "ispublic",
        "isfriend",
        "isfamily",
        "description",
        "url_z",
        "height_z",
        "width_z",
        "url_m",
        "height_m",
        "width_m"
})
public class Photo implements Parcelable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("server")
    private String server;
    @JsonProperty("farm")
    private Integer farm;
    @JsonProperty("title")
    private String title;
    @JsonProperty("ispublic")
    private Integer ispublic;
    @JsonProperty("isfriend")
    private Integer isfriend;
    @JsonProperty("isfamily")
    private Integer isfamily;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("url_z")
    private String urlZ;
    @JsonProperty("height_z")
    private String heightZ;
    @JsonProperty("width_z")
    private String widthZ;
    @JsonProperty("url_m")
    private String urlM;
    @JsonProperty("height_m")
    private String heightM;
    @JsonProperty("width_m")
    private String widthM;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    @JsonProperty("farm")
    public Integer getFarm() {
        return farm;
    }

    @JsonProperty("farm")
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("ispublic")
    public Integer getIspublic() {
        return ispublic;
    }

    @JsonProperty("ispublic")
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    @JsonProperty("isfriend")
    public Integer getIsfriend() {
        return isfriend;
    }

    @JsonProperty("isfriend")
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    @JsonProperty("isfamily")
    public Integer getIsfamily() {
        return isfamily;
    }

    @JsonProperty("isfamily")
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    @JsonProperty("description")
    public Description getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Description description) {
        this.description = description;
    }

    @JsonProperty("url_z")
    public String getUrlZ() {
        return urlZ;
    }

    @JsonProperty("url_z")
    public void setUrlZ(String urlZ) {
        this.urlZ = urlZ;
    }

    @JsonProperty("height_z")
    public String getHeightZ() {
        return heightZ;
    }

    @JsonProperty("height_z")
    public void setHeightZ(String heightZ) {
        this.heightZ = heightZ;
    }

    @JsonProperty("width_z")
    public String getWidthZ() {
        return widthZ;
    }

    @JsonProperty("width_z")
    public void setWidthZ(String widthZ) {
        this.widthZ = widthZ;
    }

    @JsonProperty("url_m")
    public String getUrlM() {
        return urlM;
    }

    @JsonProperty("url_m")
    public void setUrlM(String urlM) {
        this.urlM = urlM;
    }

    @JsonProperty("height_m")
    public String getHeightM() {
        return heightM;
    }

    @JsonProperty("height_m")
    public void setHeightM(String heightM) {
        this.heightM = heightM;
    }

    @JsonProperty("width_m")
    public String getWidthM() {
        return widthM;
    }

    @JsonProperty("width_m")
    public void setWidthM(String widthM) {
        this.widthM = widthM;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.owner);
        dest.writeString(this.secret);
        dest.writeString(this.server);
        dest.writeValue(this.farm);
        dest.writeString(this.title);
        dest.writeValue(this.ispublic);
        dest.writeValue(this.isfriend);
        dest.writeValue(this.isfamily);
        dest.writeParcelable(this.description, flags);
        dest.writeString(this.urlZ);
        dest.writeString(this.heightZ);
        dest.writeString(this.widthZ);
        dest.writeString(this.urlM);
        dest.writeString(this.heightM);
        dest.writeString(this.widthM);
    }

    public Photo() {
    }

    protected Photo(Parcel in) {
        this.id = in.readString();
        this.owner = in.readString();
        this.secret = in.readString();
        this.server = in.readString();
        this.farm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.ispublic = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isfriend = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isfamily = (Integer) in.readValue(Integer.class.getClassLoader());
        this.description = in.readParcelable(Description.class.getClassLoader());
        this.urlZ = in.readString();
        this.heightZ = in.readString();
        this.widthZ = in.readString();
        this.urlM = in.readString();
        this.heightM = in.readString();
        this.widthM = in.readString();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
