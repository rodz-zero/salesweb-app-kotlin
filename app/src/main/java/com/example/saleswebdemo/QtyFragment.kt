package com.example.saleswebdemo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class QtyFragment() : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_qty, container, false)

        var et=v.findViewById<EditText>(R.id.et_qty)
        var btn=v.findViewById<Button>(R.id.btn_qty)

        btn.setOnClickListener{
            var mobile = UserInfo.mobile
            var qty = UserInfo.qty
            var item_id = UserInfo.itemId
            var url = "${IPAddress().getIP}/SalesWeb/add_temp.php?mobile=$mobile&itemid=" +
                    "$item_id&qty=${et.text.toString()}"
            var rq:RequestQueue=Volley.newRequestQueue(activity)
            var sr=StringRequest(Request.Method.GET,url, { response ->
                var i=Intent(activity,OrderAct::class.java)
                startActivity(i)
            }, { error ->
                Toast.makeText(activity,error.message,Toast.LENGTH_LONG).show()
            })

            rq.add(sr)
        }
        return v
    }


}