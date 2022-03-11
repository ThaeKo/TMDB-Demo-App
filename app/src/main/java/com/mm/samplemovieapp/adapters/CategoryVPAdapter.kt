package com.mm.samplemovieapp.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mm.samplemovieapp.fragments.ComingSoonFragment
import com.mm.samplemovieapp.fragments.MovieFragment

class CategoryVPAdapter(
    childFragmentManager: FragmentManager,context : Context,mList: MutableList<String>
) :
    FragmentStatePagerAdapter(childFragmentManager) {

    var movieFg : MovieFragment?= null
    var blankFg : ComingSoonFragment?= null
    var mContext = context
    var list : MutableList<String> ?= null

    init {
        movieFg = MovieFragment()
        blankFg = ComingSoonFragment()
        list = mList
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> movieFg!!
            1 ->blankFg!!
            else -> return ComingSoonFragment()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {

        return list!![position]

    }

    override fun getCount(): Int {
        return list?.size ?: 0
    }

    fun setListData(mList : MutableList<String>){
        list = mList
        notifyDataSetChanged()
    }

}