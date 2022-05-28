package com.sortscript.amdsystem.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Root{
    @SerializedName("Message")
    public String message;
    @SerializedName("Status")
    public int status;
    public ArrayList<Recommendation> recommendation;
}
