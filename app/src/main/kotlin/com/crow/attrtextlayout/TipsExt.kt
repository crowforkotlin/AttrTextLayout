package com.crow.attrtextlayout

import android.util.Log

const val TIPS_TAG = "Crow-AttrTextLayout"

fun Any?.log(level: Int = Log.INFO, tag: String = TIPS_TAG) { Log.println(level, tag, this.toString()) }