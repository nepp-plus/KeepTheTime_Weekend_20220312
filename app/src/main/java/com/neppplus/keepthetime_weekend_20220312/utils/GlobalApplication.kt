package com.neppplus.keepthetime_weekend_20220312.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "d633f3ba67c3cf8cb48456cf99c7436b")

    }

}