package com.example.asus.androidscruminftel;

import org.json.JSONException;
import org.json.JSONObject;



public class Usuario {


     private String userName;
    private String userEmail;
    private String userImage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }


    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String urlImage) {
        this.userImage = urlImage;
    }


    public Usuario(String name, String email, String urlImage) {

        this.userName = name;
        this.userEmail = email;
        this.userImage = urlImage;
    }


    public String toJSON(){

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("userName", getUserName());
            jsonObject.put("userEmail", getUserEmail());
            jsonObject.put("userImage", getUserImage());
         return jsonObject.toString();


        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Usuario(String userJson){
        try {
            JSONObject jsonObject = new JSONObject(userJson);

            this.userName = jsonObject.getString("userName");
            this.userEmail = jsonObject.getString("userEmail");
            this.userImage = jsonObject.getString("userImage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

