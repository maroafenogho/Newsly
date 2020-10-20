package com.maro.newsly.data;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("response")
    private Response response;

    public News(Response response) {
        this.response = response;
    }
    public News() {}

    public Response getResponse() {
        return response;
    }
}
