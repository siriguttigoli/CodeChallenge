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
        "_content"
})
public class Description implements Parcelable {

    @JsonProperty("_content")
    private String content;

    @JsonProperty("_content")
    public String getContent() {
        return content;
    }

    @JsonProperty("_content")
    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
    }

    public Description() {
    }

    protected Description(Parcel in) {
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Description> CREATOR = new Parcelable.Creator<Description>() {
        @Override
        public Description createFromParcel(Parcel source) {
            return new Description(source);
        }

        @Override
        public Description[] newArray(int size) {
            return new Description[size];
        }
    };
}
