package com.example.gizem.ybunews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentMain extends Fragment {



   // private FragmentNews.OnFragmentInteractionListener mListener;

    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

    //    return inflater.inflate(R.layout.fragment_fragment_main, container, false);
        return inflater.inflate(R.layout.activity_main, container, false);

    }




}
