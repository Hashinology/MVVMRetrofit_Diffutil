package com.hashinology.mvvmretrofit_diffutil.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.toast(context: Context, msg: String){
    Toast.makeText(context, msg.toString(), Toast.LENGTH_LONG).show()
}