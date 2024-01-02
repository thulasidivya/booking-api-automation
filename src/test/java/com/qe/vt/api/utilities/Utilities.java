package com.qe.vt.api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String getDateAndTime() {

        return new SimpleDateFormat("dd-mmm-yy-hh-mm-ss-sss").format(new Date());

    }

    public static void threadSleep(long waitTime) {
        try {
            Thread.sleep(waitTime);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}
