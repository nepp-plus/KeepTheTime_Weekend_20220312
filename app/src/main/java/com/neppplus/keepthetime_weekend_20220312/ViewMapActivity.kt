package com.neppplus.keepthetime_weekend_20220312

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_weekend_20220312.databinding.ActivityViewMapBinding
import com.neppplus.keepthetime_weekend_20220312.datas.AppointmentData

// 도전 과제
// 네이버 지도를 화면 가득 띄우기. (라이브러리는 이미 설치 됨)
//   - 약속 장소의 좌표로 카메라 이동 / 마커 띄우기

class ViewMapActivity : BaseActivity() {

    lateinit var binding: ActivityViewMapBinding

    lateinit var mAppointmentData: AppointmentData  // 화면에 넘겨준 약속 자체

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_map)

        mAppointmentData = intent.getSerializableExtra("appointment") as AppointmentData

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        약속이름을 화면의 제목으로.
        txtTitle.text = mAppointmentData.title

    }
}