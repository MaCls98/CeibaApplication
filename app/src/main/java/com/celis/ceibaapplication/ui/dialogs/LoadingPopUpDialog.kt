package com.celis.ceibaapplication.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.celis.ceibaapplication.R

class LoadingPopUpDialog(context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }

    companion object {

        private var loadingDialog: LoadingPopUpDialog? = null

        fun startLoading(context: Context){
            loadingDialog = LoadingPopUpDialog(context)
            loadingDialog?.apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                setCancelable(false)
                show()
            }
        }

        fun closePopup() {
            loadingDialog?.dismiss()
        }
    }
}