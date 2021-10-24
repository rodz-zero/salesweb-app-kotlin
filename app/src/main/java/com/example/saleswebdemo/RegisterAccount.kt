package com.example.saleswebdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_account.*


class RegisterAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_account)

        btn_register.setOnClickListener{
            val name = reg_name.text.toString()
            val address = reg_address.text.toString()
            val mobile = reg_mobile.text.toString()
            val password = reg_password.text.toString()
            val cpassword = reg_cPassword.text.toString()

            if (password.equals(cpassword)){
                var url = "${IPAddress().getIP}/SalesWeb/add_user.php?mobile=" + mobile +
                        "&password=" + password + "&cpassword=" + cpassword + "&name=" + name +
                        "&address=" + address
                var rq:RequestQueue = Volley.newRequestQueue(this)
                var sr=StringRequest(Request.Method.GET,url, { response ->
                    if (response.equals(0)){
                        Toast.makeText(this,"Mobile already used", Toast.LENGTH_LONG).show();
                    }
                    else{
                        var i=Intent(this, HomeAct::class.java)
                        startActivity(i)
                    }
                }, { error->
                    Toast.makeText(this,error.message, Toast.LENGTH_LONG).show();
                })

                rq.add(sr)

            }
            else
                Toast.makeText(this,"Password not match", Toast.LENGTH_LONG).show();


        }


    }
}