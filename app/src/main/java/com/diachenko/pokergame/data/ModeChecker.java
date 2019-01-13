package com.diachenko.pokergame.data;

import android.content.Context;

public interface ModeChecker {

    ApplicationMode chooseMode(Context context, InternetConnector connector);
}
