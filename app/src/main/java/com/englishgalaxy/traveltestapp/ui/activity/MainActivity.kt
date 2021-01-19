package com.englishgalaxy.traveltestapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.englishgalaxy.traveltestapp.R
import com.englishgalaxy.traveltestapp.ui.fragment.ClickListenerButton
import com.englishgalaxy.traveltestapp.ui.fragment.LoginFragment
import com.englishgalaxy.traveltestapp.ui.fragment.MapFragment

class MainActivity : AppCompatActivity(), ClickListenerButton {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, LoginFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }

    override fun onClickListener(email: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container,MapFragment.newInstance(email))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}