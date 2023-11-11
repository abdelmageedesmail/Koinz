package com.abdelmageed.flickersimages.extensions

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.abdelmageed.flickersimages.R

fun initProgressDialog(context: Context, fullScreen: Boolean): Dialog {
    val progressDialog =
        if (fullScreen) Dialog(
            context,
            android.R.style.Theme_Material_Light_NoActionBar_Fullscreen
        ) else Dialog(context)
    progressDialog.apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.dialog_loading)
        window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }
    return progressDialog
}