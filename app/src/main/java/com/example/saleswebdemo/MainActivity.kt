package com.example.saleswebdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_register.setOnClickListener{
            var i = Intent(this,RegisterAccount::class.java)
            startActivity(i)
        }

        btn_login.setOnClickListener{
            val mobile = login_mobile.text.toString()
            val password = login_password.text.toString()

            var url = "${IPAddress().getIP}/SalesWeb/login.php?mobile=" + mobile +
                    "&password=" + password
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url, { response ->
                if (response.equals(0)){
                    Toast.makeText(this,"Access Denied!", Toast.LENGTH_LONG).show();
                }
                else{
                    UserInfo.mobile = mobile
                    var i=Intent(this, HomeAct::class.java)
                    startActivity(i)
                }
            }, { error->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show();
            })

            rq.add(sr)
        }
    }

}