package com.example.swarmapp;

import com.google.gson.annotations.SerializedName;

public class Post {

    private  String Name;
    private String id;
    private  String desc;

    @SerializedName("location")
    private  String content;

    public Post(String name, String content) {
        Name = name;
        this.content = content;
    }

    public String getName() {
        return Name;
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
