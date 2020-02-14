package com.kiowa.juniorandroidtechtask


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kiowa.juniorandroidtechtask.models.Country
import kotlinx.android.synthetic.main.additional_country_info.view.*
import java.text.DecimalFormat


class ExtraDetailsDialog(private var country: Country) : DialogFragment(),OnMapReadyCallback {
    private val TAG = "ExtraDetailsFragment"
    private lateinit var mButton : ImageButton
    private lateinit var mCapital : TextView
    private lateinit var mName : TextView
    private lateinit var mRegion : TextView
    private lateinit var mPopulation : TextView
    private lateinit var mArea : TextView
    private lateinit var mGoogleMap : MapView
    private lateinit var mapViewLocation : GoogleMap
    private val customParser = CustomParser()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mButton = view.backToList
        mName = view.name
        mArea=view.size
        mCapital=view.capital
        mPopulation=view.population
        mRegion=view.region
        mGoogleMap=view.map
        mButton.setOnClickListener {
            Log.d(TAG,"Closing dialog")
            dialog?.dismiss()
        }
        mName.text=country.name
        mRegion.text=country.region
        mArea.text=customParser.parseArea(country.area)
        mPopulation.text=customParser.parsePopulation(country.population)
        mCapital.text=country.capital


        mGoogleMap.onCreate(null)
        mGoogleMap.onResume()
        mGoogleMap.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        MapsInitializer.initialize(context)
        mapViewLocation = p0
        mapViewLocation.mapType=GoogleMap.MAP_TYPE_NORMAL
        mapViewLocation.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
            isCompassEnabled = false
        }
        //below is added to circumvent issues with the API not returning a LatLng for one specific region
        val latLng : LatLng = if(country.latlng.isEmpty()){
            LatLng(19.055494,-175.216251)
        }else{
            LatLng(country.latlng[0],country.latlng[1])
        }

        //place marker

        val markerOptions = MarkerOptions().also {

            //marker icon
            it.position(latLng)
            it.title(country.name)
            it.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        }
        mapViewLocation.addMarker(markerOptions)
        mapViewLocation.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.additional_country_info,container,false)
    }

    override fun onStart() {
        super.onStart()
        val dialog =dialog
        if(dialog!=null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width,height)
        }
    }

}