package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("channelID")
    @Expose
    private String channelID;

    @SerializedName("deviceType")
    @Expose
    private String deviceType;

    public LoginBody(String email, String password, String channelID, String deviceType) {
        this.email = email;
        this.password = password;
        this.channelID = channelID;
        this.deviceType = deviceType;
    }
}
