package com.tridev.materialcontainertransform

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tridev.materialcontainertransform.Constants.EXTRA_ITEM
import com.tridev.materialcontainertransform.Constants.EXTRA_TRANSITION_NAME
import com.tridev.materialcontainertransform.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), ItemAdapter.ClickListener {

    private lateinit var mBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding =
            FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
    }


    private fun setupRecyclerview() {

        val itemList = mutableListOf<Item>()
        itemList.add(Item(1, R.drawable.ic_avatar, "Avatar"))
        itemList.add(Item(2, R.drawable.ic_avengers, "Avengers: End Game"))
        itemList.add(Item(3, R.drawable.ic_batman, "Dark Knight Rises"))
        itemList.add(Item(4, R.drawable.ic_suicide, "Suicide squad"))

        val itemAdapter = ItemAdapter(this).apply {
            differ.submitList(itemList)
        }

        mBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = itemAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(startView: View, item: Item, transitionName: String) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
            requireActivity(),
            startView,
            transitionName // The transition name to be matched in Activity B.
        )

        val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(EXTRA_TRANSITION_NAME, transitionName)
            putExtra(EXTRA_ITEM, item as Parcelable)
        }
        startActivity(intent, options.toBundle())

    }
}