package com.neppplus.keepthetime_weekend_20220312.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_weekend_20220312.datas.UserData

class MyFriendRecyclerAdapter(
    val mContext: Context,
    val mList: List<UserData> // 뷰 홀더 사용하면, resId를 받지 않아도 됨.
) : RecyclerView.Adapter<MyFriendRecyclerAdapter.MyViewHolder>() {

//    클래스 내부의 클래스 (inner class) 제작 > MyFriendRecyclerAdapter가 혼자 사용.

    inner class  MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }

}