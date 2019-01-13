package com.diachenko.pokergame.data;

import org.json.JSONObject;

public interface InternetConnector {

    JSONObject connect(String url);
}
