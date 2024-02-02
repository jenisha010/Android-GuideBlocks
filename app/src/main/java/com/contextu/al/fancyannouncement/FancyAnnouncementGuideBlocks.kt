package com.contextu.al.fancyannouncement

import android.app.Activity
import android.content.DialogInterface
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.contextu.al.R

class FancyAnnouncementGuideBlocks(private val activity: Activity) {

    fun show(title: String, content: String, negativeText: String, negativeButtonListener: DialogInterface.OnClickListener,
             positiveText: String, positiveButtonListener: DialogInterface.OnClickListener, imageUrl: String){
       // val view = activity.layoutInflater.inflate(R.layout.fancy_annoucenment, null, true)
        //val viewGroup = (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        val alertDialog = AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(content)
            .setNegativeButton(negativeText, negativeButtonListener)
            .setPositiveButton(positiveText, positiveButtonListener)
            .create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setContentView(R.layout.fancy_announcement)
        alertDialog.show()
//        val fancyAnnouncementImage = alertDialog.findViewById<ImageView>(R.id.announcementImage)
//        fancyAnnouncementImage?.let {
//            Glide.with(activity.baseContext).load(imageUrl).into(fancyAnnouncementImage)
//        }
//        val fancyAnnouncementTitle = alertDialog.findViewById<TextView>(R.id.fancy_announcement_title)
//        fancyAnnouncementTitle?.text = title
//        val fancyAnnouncementContent = alertDialog.findViewById<TextView>(R.id.fancy_announcement_message)
//        fancyAnnouncementContent?.text = content
//        val imageView = view.findViewById<ImageView>(R.id.fancy_announcement_imageview)
//        viewGroup.addView(imageView)
//        Glide.with(activity.baseContext).load(imageUrl).into(imageView)

    }
}