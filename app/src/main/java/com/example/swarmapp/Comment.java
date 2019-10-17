package com.example.swarmapp;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private  String name;
    private  String id;
    private  String desc;


    @SerializedName("location")
    private  String content;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getContent() {
        return content;
    }
}
