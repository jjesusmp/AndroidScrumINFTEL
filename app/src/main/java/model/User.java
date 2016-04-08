package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Asus on 06/04/2016.
 */
public class User {
    private String email;
    private String idusuario;
    private String nombre;
    private String refreshToken;

    public User(String email, String idusuario, String nombre, String refreshToken) {
        this.email = email;
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.refreshToken = refreshToken;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toJSON(){

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("email", getEmail());
            jsonObject.put("idusuario", getIdusuario());
            jsonObject.put("nombre", getNombre());
            jsonObject.put("refreshToken", getRefreshToken());
            return jsonObject.toString();


        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public User(String userJson){
        try {
            JSONObject jsonObject = new JSONObject(userJson);

            this.nombre = jsonObject.getString("nombre");
            this.email = jsonObject.getString("email");
            this.idusuario = jsonObject.getString("idusuario");
            this.refreshToken = jsonObject.getString("refreshtoken");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
