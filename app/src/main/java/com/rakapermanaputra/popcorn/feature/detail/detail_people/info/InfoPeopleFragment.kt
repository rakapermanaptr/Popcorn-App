package com.rakapermanaputra.popcorn.feature.detail.detail_people.info


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ImagesPeopleAdapter
import com.rakapermanaputra.popcorn.model.ImagesPeople
import com.rakapermanaputra.popcorn.model.PeopleDetail
import com.rakapermanaputra.popcorn.model.repository.PeopleRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import kotlinx.android.synthetic.main.fragment_info_people.*


class InfoPeopleFragment : androidx.fragment.app.Fragment(), InfoPeopleContract.View {

    private lateinit var presenter: InfoPeoplePresenter
    private var imagesPeople: MutableList<ImagesPeople> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getInt("id")

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = PeopleRepoImpl(service)
        presenter = InfoPeoplePresenter(this, request)
        presenter.getDetail(id)
        presenter.getImages(id)
    }

    override fun showDetail(detail: PeopleDetail) {
        tvOvwPeople.text = detail.biography
        tvBorn.text = detail.birthday
        tvBirthplace.text = detail.place_of_birth

        if (detail.homepage == null){
            tvHomepage.text = "N/A"
        } else {
            tvHomepage.text = detail.homepage
        }

        if (detail.birthday == null) {
            tvAge.text = "N/A"
        } else {
            val age = 2019 - (detail.birthday.substring(0, 4).toInt())
            tvAge.text = age.toString()
        }


        for (knownAs in detail.also_known_as!!) {
            tvKnownAs.append("${knownAs}, ")
        }

    }

    override fun showImages(images: List<ImagesPeople>) {
        imagesPeople.clear()
        imagesPeople.addAll(images)
        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                LinearLayout.HORIZONTAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = ImagesPeopleAdapter(requireContext(), imagesPeople)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_people, container, false)
    }


}
