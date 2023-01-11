package com.tridev.materialcontainertransform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tridev.materialcontainertransform.MaterialTransitionUtils.applyExitMaterialTransform

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        applyExitMaterialTransform()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment())
            .commit()

    }
}