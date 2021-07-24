package net.s1mple.ulanganlaravel.ui.agent

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant

class AgentMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient // lokasi gabungan
    private lateinit var lastLocation: Location // data lokasi yg akan disimpan
    private val marker = MarkerOptions()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        supportActionBar?.title = "Lokasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true // buat nge Zoom gan
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
            return
        }
        googleMap.isMyLocationEnabled = true

        // Untuk mengetahui posisi lokasi kita di GMaps
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
            if (it != null) {
                lastLocation = it
                val currentLatLng = LatLng(it.latitude, it.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))

                Constant.LATITUDE  = it.latitude.toString()
                Constant.LONGITUDE = it.longitude.toString()

                marker.position(currentLatLng) // Markernya mengikuti posisi kita sekarang
                googleMap.addMarker(marker)
            }
        }

        // untuk merubah posisi marker
        googleMap.setOnMapClickListener {
            marker.position(it)
            marker.title("${it.latitude} : ${it.longitude}")

            Constant.LATITUDE  = it.latitude.toString()
            Constant.LONGITUDE = it.longitude.toString()
            Log.d("MantapGan", "Lat ${Constant.LATITUDE} Lng ${Constant.LONGITUDE}")

            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(it)) // kameranya gerak sesuai lokasi yg dipilih
            googleMap.addMarker(marker) // markernya berpindah sesuai yg kita klik
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_maps, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save -> {
                Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}