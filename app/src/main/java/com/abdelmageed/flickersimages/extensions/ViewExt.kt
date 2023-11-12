package com.abdelmageed.flickersimages.extensions

import android.view.View
import com.abdelmageed.flickersimages.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.viewIsVisible() {
    this.visibility = View.VISIBLE
}

fun View.viewIsGone() {
    this.visibility = View.GONE
}

fun View.showSnackBar(isInternetConnected: Boolean, clickListener: (Int) -> Unit) {
    val snackbar = Snackbar.make(this, "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction("Try Again", View.OnClickListener {
        // executed when DISMISS is clicked
        if (this.context.isOnline()) {
            clickListener(0)
        }
    })



    snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onShown(transientBottomBar: Snackbar?) {
            super.onShown(transientBottomBar)
            transientBottomBar?.view?.findViewById<View>(com.google.android.material.R.id.snackbar_action)
                ?.setOnClickListener {
                    if (transientBottomBar.context.isOnline()) {
                        clickListener(0)
                        transientBottomBar.dismiss()
                    } else {
                        transientBottomBar.show()
                    }
                }
        }

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            if (!isInternetConnected)
                snackbar.show()
        }
    })

    snackbar.show()
}