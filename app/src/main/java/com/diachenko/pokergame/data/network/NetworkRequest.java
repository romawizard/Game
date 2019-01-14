package com.diachenko.pokergame.data.network;

import org.json.JSONObject;

public interface NetworkRequest {

    JSONObject request(String url);
}
