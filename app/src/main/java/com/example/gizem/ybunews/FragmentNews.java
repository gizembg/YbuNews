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

public class FragmentNews extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    //private ListView list;
    private TextView txt;



    // TODO: Rename and change types of parameters
    private String mParam1;
//    private String mParam2;

    //  private OnFragmentInteractionListener mListener;

    public FragmentNews() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentNews newInstance(String param1) {
        FragmentNews fragment = new FragmentNews();
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
        //    mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_news, container, false);
        Toast.makeText(getActivity(),"Fragment news", Toast.LENGTH_SHORT).show();
        txt =(TextView) v.findViewById(R.id.contentnews);
        //list =(ListView) v.findViewById(R.id.contentnews);

        // txt.setText("Welcome " + mParam1);


        Thread t = new Thread(new Runnable() {
            Document doc;
            Element element;
            @Override
            public void run() {
                try {
                    doc = Jsoup.connect("http://ybu.edu.tr/muhendislik/bilgisayar/").get();
                    element = doc.select("div.contentNews").first();
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

    // TODO: Rename method, update argument and hook method into UI event
 //   public void onButtonPressed(Uri uri) {
 //       if (mListener != null) {
 //           mListener.onFragmentInteraction(uri);
 //       }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
    //                  + " must implement OnFragmentInteractionListener");
    //    }
    // }

  //  @Override
 //   public void onDetach() {
 //       super.onDetach();
 //       mListener = null;
 //   }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
