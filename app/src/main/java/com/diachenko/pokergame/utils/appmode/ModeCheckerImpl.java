package com.diachenko.pokergame.utils.appmode;

import android.content.Context;
import android.text.TextUtils;

import com.diachenko.pokergame.data.enteties.FileConfiguration;
import com.diachenko.pokergame.data.network.NetworkConnection;
import com.diachenko.pokergame.data.network.NetworkRequest;
import com.diachenko.pokergame.utils.Constants;
import com.diachenko.pokergame.utils.MyLog;
import com.google.gson.Gson;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ModeCheckerImpl implements ModeChecker {

    public static final String TAG = ModeCheckerImpl.class.getSimpleName();

    private final String LINK = "link";
    private final String APP = "application";
    private NetworkConnection networkConnection;
    private NetworkRequest connector;


    @Inject
    public ModeCheckerImpl(NetworkConnection networkConnection, NetworkRequest connector) {
        this.networkConnection = networkConnection;
        this.connector = connector;
    }

    @Override
    public ApplicationMode chooseMode(Context context) {

        if (networkConnection.hasConnection(context)) {
            try {
                MyLog.log(TAG, "network connection is present");
                JSONObject jsonObject = connector.request(Constants.URL_TEXT_FILE);
                Gson gson = new Gson();

                FileConfiguration configuration = gson.fromJson(jsonObject.toString(),
                        FileConfiguration.class);

                MyLog.log(TAG, configuration.toString());

                if (TextUtils.equals(configuration.getOpen(), LINK)) {
                    return ApplicationMode.linkMode(configuration.getOpenLink());
                } else {
                    if (TextUtils.equals(configuration.getOpen(), APP)) {
                        return ApplicationMode.appMode();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ApplicationMode.appMode();
    }
}

