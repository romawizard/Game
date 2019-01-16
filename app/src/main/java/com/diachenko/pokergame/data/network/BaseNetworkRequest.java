package com.diachenko.pokergame.data.network;

import com.diachenko.pokergame.utils.MyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Singleton;
import javax.net.ssl.HttpsURLConnection;

@Singleton
public class BaseNetworkRequest implements NetworkRequest {

    public static final String TAG = BaseNetworkRequest.class.getSimpleName();

    @Override
    public JSONObject request(String url) {

        HttpsURLConnection connection;
        BufferedReader reader;
        JSONObject jsonObject = null;

        try {
            URL myUrl = new URL(url);

            connection = (HttpsURLConnection) myUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = (InputStream) connection.getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            MyLog.log(TAG, "json" + responseStrBuilder.toString());
            jsonObject = new JSONObject(responseStrBuilder.toString());

            //returns the json object
            return jsonObject;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //if something went wrong, return null
        return null;

    }
}
