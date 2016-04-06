package com.example.asus.androidscruminftel;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtilService extends Service {

    private String userJson;

    public HttpUtilService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class doPostTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {

            try{
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "Unable to retrieve web page.URL may be invalid";
            }
        }
        @Override
        protected void onPostExecute(String result){
        }
        private String downloadUrl(String myurl) throws IOException{
            InputStream is = null;
            try{
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(1000 /*miliseconds*/);
                conn.setConnectTimeout(1500 /*miliseconds*/);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(userJson);
                osw.flush();
                conn.connect();
                StringBuilder sb = new StringBuilder();
                int HttpResult = conn.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println("" + sb.toString());
                } else {
                    System.out.println(conn.getResponseMessage());
                }
            } finally{
                if(is!=null){
                    is.close();
                }
            }
            return "";
        }
    }

}
