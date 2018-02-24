package com.ujikit.man2yk;

import android.app.Fragment;
import android.app.ListFragment;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ujikit on 12/02/18.
 */

public class b_home_cek_nilai_siswa_smt1 extends android.support.v4.app.ListFragment{

    String[] players = {"Bambang","Tukino","Paijo"};
    int[] images = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};

    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //MAP
        HashMap<String, String> map = new HashMap<String, String>();

        //FILL
        for (int i=0;i<players.length;i++){
            map = new HashMap<String, String>();
            map.put("Player", players[i]);
            map.put("Image", Integer.toString(images[i]));
            data.add(map);
        }

        //KEY IN MAP
        String[] from = {"Player","Image"};

        //IDS OF VIEWS
        int[] to = {R.id.nameTxt, R.id.imageView1};

        //ADAPTER
        adapter = new SimpleAdapter(getActivity(), data, R.layout.fragment_b_home_cek_nilai_siswa_smt1, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onStart(){
        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Toast.makeText(getActivity(), data.get(pos).get("Player"), Toast.LENGTH_SHORT).show();
            }
        });

    }
}