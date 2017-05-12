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
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_food, container, false);
        Toast.makeText(getActivity(),"Fragment food", Toast.LENGTH_SHORT).show();
        txt =(TextView) v.findViewById(R.id.textFood);
        //list =(ListView) v.findViewById(R.id.contentnews);

        // txt.setText("Welcome " + mParam1);


        Thread t = new Thread(new Runnable() {
            Document doc;
            Element element;
            Elements links;
            String txtt;
             String url;
            @Override
            public void run() {
                try {
                    doc = Jsoup.connect("http://ybu.edu.tr/sks/category_title_list-576-aybu-beslenme.html").get();
                    element = doc.select("span.alternative_list").first();

                   //element = doc.getElementById("ContentPlaceHolder1_Content_Title_List1_rpData_hplink_3");

                    links = element.getElementsByTag("a");
                    for (Element link: links) {
                      url = link.attr("href");
                        txtt = link.text();
                        System.out.println(txtt + ", " + url);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            //  list.addView(element.text().toString());
                            txt.setText(url);

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



