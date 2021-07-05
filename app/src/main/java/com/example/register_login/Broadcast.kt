package com.example.register_login

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.jar.Manifest

class Broadcast : AppCompatActivity() {
lateinit var fusedLocationProviderClient: FusedLocationProviderClient
var area:String = "abs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        var pin: String? = intent.getStringExtra("pin")
        Log.d("MAIN","$pin")
        var Topic = "/topics/$pin"
        FirebaseMessaging.getInstance().subscribeToTopic(Topic)
        var broadCastBtn=findViewById<Button>(R.id.btnBroadCast)
        val tit = findViewById<TextInputEditText>(R.id.edTitleTxt)
        val msg = findViewById<TextInputEditText>(R.id.edtxtMsg)
      fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        broadCastBtn.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),1)
            }
            else
            {//getgeoLocation()
                var randomLoc:String ="area"
                fusedLocationProviderClient.lastLocation?.addOnSuccessListener {

                    if(it==null)
                    {
                        Toast.makeText(this,"Sorry cant't get location",Toast.LENGTH_SHORT);
                    }
                    else it.apply{
                        val lat = it.latitude;
                        val longi= it.longitude;
                        area=""+latitude+" "+longitude
                        val title = tit.text.toString()
                        val message = msg.text.toString()
                        //TODO:work to be done
                        Log.d("BroadCast",area);
                        val mob:String="9258441169"
                        val name:String="Ajay Singh"
                        val latitude:String = it.latitude.toString()
                        val longitude:String=it.longitude.toString()

                        if(title.isNotEmpty()&&message.isNotEmpty()&&area.isNotEmpty())
                        {
                            pushNotification(NotificationData(title,message,mob,name,latitude,longitude), Topic).also {
                                Log.d("DEB","$title"+" "+"$latitude")
                                sendNotification(it)
                            }
                        }

                    }

                }

            }

        }
    }

//    private fun checkPermissions() {
//
//        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),1)
//        }
//        else
//        {
//           // getlocation()
//
//        }

//    private fun fetchLocation():Location{
//      //  var area:String="abc"
//        var location:Location
//        val task =fusedLocationProviderClient.lastLocation
//        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
//
//        }
//        task.addOnSuccessListener {
//            if(it!=null)
//            {
//                area=it.latitude.toString()+" "+it.longitude.toString();
//
//            }
//            else
//            {
//
//            }
//        }



//    @SuppressLint("MissingPermission")
//    private fun getlocation() :String
//    {
//       var randomLoc:String ="area"
//        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
//
//      if(it==null)
//      {
//           Toast.makeText(this,"Sorry cant't get location",Toast.LENGTH_SHORT);
//      }
//      else it.apply{
//           val latitude = it.latitude;
//          val longitude= it.longitude;
//          area=""+latitude+" "+longitude
//          }
//
//     }
//        if(area.isNotEmpty())
//        return area
//        return randomLoc
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(requestCode==1)
//        {
//            if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
//            {
//                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
//                {
//                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
//                    getlocation()
//                }
//                else
//                {
//                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT);
//                }
//
//            }
//
//        }
//    }


    private fun sendNotification(notification:pushNotification)= CoroutineScope(Dispatchers.IO).launch {
        try{
            val response = Retrofitinstance.api.postNotification(notification)
        }catch (e:Exception){
            Log.i("Main",e.toString())
        }
    }


}
