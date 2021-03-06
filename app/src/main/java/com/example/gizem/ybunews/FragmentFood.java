package com.example.gizem.ybunews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FragmentFood extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txt;

    public WebView mWebView;

    public FragmentFood() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentFood.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFood newInstance(String param1) {
        FragmentFood fragment = new FragmentFood();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
                             Bundle savedInstanceState)

    {

        View v = inflater.inflate(R.layout.fragment_fragment_food, container, false);



        mWebView = (WebView) v.findViewById(R.id.foodweb);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();
        txt = (TextView) v.findViewById(R.id.textFood);

        Thread t = new Thread(new Runnable() {
            Document doc;
            Elements element;
            String urlMenu;
            @Override
            public void run() {
                try {
                    doc = Jsoup.connect("http://ybu.edu.tr/sks/").get();
                    element = doc.select("a[id=wucUstMenu1_rpData_rpData_2_hplink_6]" );
                    urlMenu =("http://ybu.edu.tr") +element.attr("href");

                    getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           mWebView.loadUrl(
                                             "http://docs.google.com/gview?embedded=true&url=" + urlMenu);
                        }
                   });
         } catch (IOException e) {
                    e.printStackTrace();
                }
           }

           });
            t.start();
            return v;
    }
}



