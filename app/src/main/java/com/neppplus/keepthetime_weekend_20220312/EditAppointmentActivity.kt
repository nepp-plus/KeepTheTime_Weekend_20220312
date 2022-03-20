package com.neppplus.keepthetime_weekend_20220312

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import com.neppplus.keepthetime_weekend_20220312.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

//    선택한 약속일시를 저장하는 Calendar 변수
    val mSelectedDatetimeCal = Calendar.getInstance()  // 현재 일시가 기본 저장. (일시 + 초 + 1/1000초)

//    지도에 띄워줄 목적지 표시 마커.
    var myMarker : Marker? = null  // 처음에는 목적지 마커도 없는 상태.

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

//                    새 양식 : 2022-03-05 양식
//                   val sdf = SimpleDateFormat("yyyy-MM-dd")

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

        binding.txtTime.setOnClickListener {

            val tsl = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {

//                    선택된 일시에, 시간/분 저장 => 시간 항목에 hourOfDay, 분 항목에 minute
                    mSelectedDatetimeCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    mSelectedDatetimeCal.set(Calendar.MINUTE,  minute)

//                    txtTime의 문구를 "오후 7시 5분" 양식으로 가공 => SimpleDateFormat 사용
                    val sdf = SimpleDateFormat("a h시 m분")

                    binding.txtTime.text = sdf.format( mSelectedDatetimeCal.time ) // Date형태인 time 변수 활용.

                }

            }

            val tpd = TimePickerDialog(
                mContext, // 어느 화면?
                tsl,
                12,
                30,
                false // 시계가 24시간 기준? 12시간 기준?
            ).show()

        }


        binding.btnSave.setOnClickListener {

//            입력한 값들 추출 => 서버에 전송

            val inputTitle = binding.edtTitle.text.toString()

//            약속 일시 : mSelectedDatetimeCal 의 일시를 => "2022-03-20 14:19:50" 형태로 가공해서 첨부.

            val serverFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val serverDateTimeStr = serverFormat.format( mSelectedDatetimeCal.time ) // 첨부할 약속 일시

            val inputPlaceName = binding.edtPlaceName.text.toString()

//            네이버 지도에 마커로 찍어둔 장소 > 서버에 전송?

//            내가 찍어둔 마커가 있다고 전제하고 코딩.

            val lat = myMarker!!.position.latitude  // 찍힌 마커의 위도 추출.
            val lng = myMarker!!.position.longitude // 찍힌 마커의 경도 추출.

//            서버에 파라미터값들 전송. (API 호출)

        }


    }

    override fun setValues() {

        binding.mapView.getMapAsync {

//            it 변수 대신, 문서와 같은 이름의 변수 naverMap 에 옮겨 담고 사용.
            val naverMap = it

//            기본 지도의 시작 화면 : 서울시청. => 네이버지도의 시작 좌표 : 넵플러스 학원

            val cameraUpdate =  CameraUpdate.scrollTo( LatLng( 37.577927550342345, 127.03360311276816 ) )
            naverMap.moveCamera( cameraUpdate )

//            넵플러스 학원 위치에, 마커를 띄워보자.

            val marker = Marker()
            marker.position =  LatLng(37.577927550342345, 127.03360311276816)
            marker.map = naverMap

//            마커 색상 변경
            marker.icon = MarkerIcons.BLACK // 이 위에 원하는 색 커스텀
            marker.iconTintColor = Color.parseColor("#FF0000") // 안드로이드가 주는 색

//            마커 크기 변경

            marker.width = 50
            marker.height = 80
            
//            네이버 지도의 클릭 이벤트
            
            naverMap.setOnMapClickListener { pointF, latLng -> 
                
//                클릭된 좌표 latLng 변수의 내용을 토스트로 출력
//                Toast.makeText(
//                    mContext,
//                    "위도 : ${latLng.latitude}, 경도 : ${latLng.longitude}",
//                    Toast.LENGTH_SHORT
//                ).show()

//                마커를 클릭된 지점에 설정.

//                myMarker가 만들어진게 없다면, 새로 마커 생성.
//                만들어진게 있다면, 기존 마커 재활용.

                if (myMarker == null) {
                    myMarker = Marker()
                }

                myMarker!!.position = latLng  // 클릭된 지점 자체를 위치로 설정.
                myMarker!!.map = naverMap

                
            }


        }

    }
}