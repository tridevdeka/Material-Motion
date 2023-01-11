package com.tridev.materialcontainertransform

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.tridev.materialcontainertransform.MaterialTransitionUtils.applyEnterMaterialTransform
import com.tridev.materialcontainertransform.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDetailBinding
    private val transitionName by lazy {
        intent.getStringExtra(Constants.EXTRA_TRANSITION_NAME)
    }
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        applyEnterMaterialTransform(transitionName)
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupToolbar()

        item = intent.getParcelableExtra("EXTRA_ITEM")
        item?.let {
            mBinding.ivPoster.setImageResource(it.drawable)
            mBinding.detailTitle.text = it.title
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}