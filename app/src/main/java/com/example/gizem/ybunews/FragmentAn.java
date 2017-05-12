package com.example.gizem.ybunews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;



public class FragmentAn extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView txt;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentAn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAn.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAn newInstance(String param1, String param2) {
        FragmentAn fragment = new FragmentAn();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_an, container, false);
        Toast.makeText(getActivity(),"Fragment An", Toast.LENGTH_SHORT).show();
        txt =(TextView) v.findViewById(R.id.textAn);

        Thread t = new Thread(new Runnable() {
            Document doc;
            Element element;
            @Override
            public void run() {
                try {
                    doc = Jsoup.connect("http://ybu.edu.tr/muhendislik/bilgisayar/").get();
                    element = doc.select("div.contentAnnouncements").first();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            //  list.addView(element.text().toString());
                            txt.setText(element.text());

                        }
                    });
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        t.start();



        return v;
    }

}
