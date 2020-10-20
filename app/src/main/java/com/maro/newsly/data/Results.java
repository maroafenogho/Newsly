package com.maro.newsly.data;

import com.google.gson.annotations.SerializedName;

public class Results {

        @SerializedName("type")
        private String type;
        @SerializedName("sectionName")
        private String sectionName;
        @SerializedName("webPublicationDate")
        private String webPublicationDate;
        @SerializedName("webTitle")
        private String webTitle;
        @SerializedName("webUrl")
        private String webUrl;

    public Results(String type, String sectionName, String webPublicationDate, String webTitle, String webUrl) {
        this.type = type;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
    }

    public Results() {}

    public String getType() {
        return type;
    }
    public String getSectionName() {
        return sectionName;
    }
    public String getWebPublicationDate() {
        return webPublicationDate;
    }
    public String getWebTitle() {
        return webTitle;
    }
    public String getWebUrl() {
        return webUrl;
    }
}
