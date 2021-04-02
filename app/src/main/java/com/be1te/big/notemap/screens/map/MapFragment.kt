package com.be1te.big.notemap.screens.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.be1te.big.notemap.R
import com.be1te.big.notemap.utilits.COORDINATE_X
import com.be1te.big.notemap.utilits.COORDINATE_Y
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {

    private lateinit var location: LatLng
    private lateinit var newLocation: LatLng

    private val callback = OnMapReadyCallback { googleMap ->

        location = if (COORDINATE_X == "0.0" && COORDINATE_Y == "0.0") {
            LatLng(0.0, 0.0)
        } else {
            LatLng(COORDINATE_X.toDouble(), COORDINATE_Y.toDouble())
        }
        googleMap.addMarker(
            MarkerOptions().position(location).draggable(true).title("MyPlace")
        )
        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(p0: Marker?) {
                if (p0 != null) {
                    newLocation = p0.position
                }
            }

            override fun onMarkerDragStart(p0: Marker?) {
            }

            override fun onMarkerDrag(p0: Marker?) {
            }
        })
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun setCoordinates() {
        COORDINATE_X = newLocation.latitude.toString()
        COORDINATE_Y = newLocation.longitude.toString()
    }
}