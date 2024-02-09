package com.contextu.al.fancyannouncement

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.contextu.al.R


class FancyAnnouncementGuideBlocks(private val activity: Activity): Dialog(activity) {

    fun show(title: String,
             content: String,
             negativeText: String,
             negativeButtonListener: View.OnClickListener,
             positiveText: String,
             positiveButtonListener: View.OnClickListener,
             imageUrl: String) {
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.fancy_announcement)
        dialog.window?.setLayout((activity.resources.displayMetrics.widthPixels * 0.90).toInt(),
            (activity.resources.displayMetrics.heightPixels * 0.55).toInt())
        dialog.show()

        val fancyAnnouncementImage = dialog.findViewById<ImageView>(R.id.announcementImage)
        fancyAnnouncementImage?.let {
            Glide.with(activity.baseContext).load(imageUrl).into(fancyAnnouncementImage)
        }

        val fancyAnnouncementTitle = dialog.findViewById<TextView>(R.id.title)
        fancyAnnouncementTitle?.text = title

        val fancyAnnouncementContent = dialog.findViewById<TextView>(R.id.content)
        fancyAnnouncementContent?.text = content

        val createAccountButton = dialog.findViewById<Button>(R.id.create_button)
        createAccountButton?.text = positiveText
        createAccountButton.setOnClickListener(positiveButtonListener)

        val cancelButton = dialog.findViewById<Button>(R.id.cancel_button)
        cancelButton?.text = negativeText
        cancelButton.setOnClickListener(negativeButtonListener)
    }
}