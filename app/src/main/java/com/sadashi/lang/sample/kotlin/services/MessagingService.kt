package com.sadashi.lang.sample.kotlin.services

import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sadashi.lang.sample.kotlin.R
import com.sadashi.lang.sample.kotlin.activities.OssLicensesActivity


/**
 * メッセージサービス
 */
class MessagingService : FirebaseMessagingService() {
    private val NOTIFICATION_ID = 1
    private val REQUEST_CODE_TEST = 100

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val intent = Intent(this, OssLicensesActivity::class.java)

        val contentIntent = PendingIntent.getActivity(applicationContext,
                REQUEST_CODE_TEST, intent, PendingIntent.FLAG_ONE_SHOT)

        val builder = NotificationCompat.Builder(applicationContext).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(remoteMessage?.notification?.title)
            setContentText(remoteMessage?.notification?.body)
            setContentIntent(contentIntent)
            setAutoCancel(true)
        }

        NotificationManagerCompat.from(applicationContext)
                .notify(NOTIFICATION_ID, builder.build())
    }
}
