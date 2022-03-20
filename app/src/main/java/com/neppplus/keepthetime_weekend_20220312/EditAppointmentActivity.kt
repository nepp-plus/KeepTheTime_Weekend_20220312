package com.neppplus.keepthetime_weekend_20220312

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_weekend_20220312.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

//    선택한 약속일시를 저장하는 Calendar 변수
    val mSelectedDatetimeCal = Calendar.getInstance()  // 현재 일시가 기본 저장. (일시 + 초 + 1/1000초)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.txtDate.setOnClickListener {

//            날짜가 선택되면 할 일 저장

            val dsl = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    year, month, dayOfMonth => 달력을 통해서 선택한 일자 정보.
//                    Toast.makeText(mContext, "${year}년 ${month}월 ${dayOfMonth}일", Toast.LENGTH_SHORT).show()

//                    선택된 일시를 저장할 변수에, 연/월/일 세팅.
                    mSelectedDatetimeCal.set(year, month, dayOfMonth)

//                    약속 일자 텍스트뷰의 문구를 "3월 5일" 형태로 가공해서 출력.
//                    Calendar(내부의 Date) 를 => String으로 가공 전문 클래스 (SimpleDateFormat) 활용.

                    val sdf = SimpleDateFormat("M월 d일")

//                    sdf로 format해낸 String을, txtDate의 문구로 반영
                    binding.txtDate.text =  sdf.format( mSelectedDatetimeCal.time )

                }

            }


//            실제로 달력 팝업 띄우기.

//            선택한 일시 (기본값 : 현재일시) 의 연/월/일을 띄워보자.

            val dpd = DatePickerDialog(
                mContext,
                dsl,
                mSelectedDatetimeCal.get( Calendar.YEAR ),  // 선택일시의 년도만 배치.
                mSelectedDatetimeCal.get( Calendar.MONTH ),
                mSelectedDatetimeCal.get( Calendar.DAY_OF_MONTH )
            ).show()

            Log.d("선택월", mSelectedDatetimeCal.get( Calendar.MONTH ).toString())

        }

    }

    override fun setValues() {

    }
}