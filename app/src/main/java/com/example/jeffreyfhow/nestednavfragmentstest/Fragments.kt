package com.example.jeffreyfhow.nestednavfragmentstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.inner_fragment_b1.*

class FragmentA : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goToBButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
        }
    }
}

class FragmentB : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    fun isInnerFragmentB2Showing(): Boolean {
        return childFragmentManager.primaryNavigationFragment?.childFragmentManager?.primaryNavigationFragment is InnerFragmentB2
    }
}

class InnerFragmentB1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inner_fragment_b1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goToB2Button.setOnClickListener {
            findNavController().navigate(R.id.action_innerFragmentB1_to_innerFragmentB2)
        }
    }
}

class InnerFragmentB2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inner_fragment_b2, container, false)
    }
}