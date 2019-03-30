package com.archeosbj.lifetarget;
import android.app.ActivityOptions;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.archeosbj.lifetarget.R;

public class mfragtest extends Fragment{
    private String Userdata[] = new String[10];
    public mfragtest() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.zero_fragment, container, false);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        //ASSIGN IMAGE VIEW FONCTION
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            TabLayout myHost = (TabLayout) getActivity().findViewById(R.id.tabs);
            myHost.setScrollPosition(1,0f,true);
            myHost.getTabAt(1).select();
        }
    }

   /* @Override
    public void onPause(){
        super.onPause();
        TabLayout myHost = (TabLayout) getActivity().findViewById(R.id.tabs);
        myHost.getTabAt(1).select();
    }*/
}
