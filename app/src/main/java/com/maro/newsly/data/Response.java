package com.maro.newsly.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {
        @SerializedName("results")
        private List<Results> results;

    public Response(List<Results> results) {
        this.results = results;
    }

    public Response() {}

    public List<Results> getResults() {
        return results;
    }
}
