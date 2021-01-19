package com.englishgalaxy.traveltestapp.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.englishgalaxy.traveltestapp.R
import com.englishgalaxy.traveltestapp.adapters.MapAdapter
import com.englishgalaxy.traveltestapp.databinding.FragmentMapBinding
import com.englishgalaxy.traveltestapp.net.responce.ItemPlaces
import com.englishgalaxy.traveltestapp.viewmodels.MapViewModel
import com.englishgalaxy.traveltestapp.viewmodels.State
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class MapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        private const val KEY = "MapKey"
        fun newInstance(email: String) = MapFragment().apply {
            arguments = Bundle().apply {
                putString(KEY, email)
            }
        }
    }

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val mapAdapter: MapAdapter = MapAdapter()
    private val viewModel: MapViewModel by inject()
    private lateinit var myMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        viewModel.liveData.observe(viewLifecycleOwner, this::setState)
    }

    private fun setState(state: State) =
        when (state) {
            is State.Default -> {
                showDefaultState()
            }
            is State.Loading -> {
                showLoadingState()
            }
            is State.Success -> {
                showSuccessState(state.itemMap)
            }
            is State.Error -> {
                showErrorState(state.error)
            }
        }

    private fun initRecyclerView(view: View) {
        binding.rvListMarker.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = mapAdapter
        }
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            title = arguments?.getString(KEY)
            setTitleTextColor(Color.WHITE)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        myMap = map!!
        val sydney = LatLng(-34.0, 151.0)
        myMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun showDefaultState() {
        binding.toolbar.isVisible = false
        binding.rvListMarker.isVisible = false
    }

    private fun showLoadingState() {
        initToolbar()
        binding.toolbar.isVisible = true
    }

    private fun showSuccessState(itemMap: ItemPlaces) {
        binding.rvListMarker.isVisible = true
        mapAdapter.setList(itemMap)
        itemMap.places.forEach {
            val place = LatLng(it.lat, it.lng)
            myMap.addMarker(MarkerOptions().position(place))
        }

    }

    private fun showErrorState(error: String) {
        Toast.makeText(this.context, error, Toast.LENGTH_SHORT).show()
    }

}