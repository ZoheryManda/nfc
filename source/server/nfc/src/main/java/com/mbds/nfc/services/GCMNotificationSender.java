package com.mbds.nfc.services;

import com.mbds.nfc.model.Notification;

public interface GCMNotificationSender {

    void sendNotification(Notification notification);

}
