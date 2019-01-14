package com.diachenko.pokergame.data.network;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetWorkConnectionImpl implements NetworkConnection {

    @Override
    public boolean hasConnection(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }
}
