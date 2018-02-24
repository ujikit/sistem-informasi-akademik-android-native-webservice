package com.ujikit.man2yk;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ujikit on 12/02/18.
 */

public class b_home_cek_nilai_siswa_smt2_backup extends android.support.v4.app.ListFragment{

    ////
    private String classtag= b_home_cek_nilai_siswa_smt2_backup.class.getSimpleName();  //return name of underlying class
    private ListView lv;
    private ProgressDialog progress;
    private String url="https://raw.githubusercontent.com/iCodersLab/JSON-Parsing-in-Android-using-Android-Studio/master/index.html"; //passing url
    ArrayList<HashMap<String,String>> dataArrayList = new ArrayList<HashMap<String, String>>(); //arraylist to save key value pair from json
    ////



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ////
        View rootView = inflater.inflate(R.layout.fragment_b_home_cek_nilai_siswa_smt2, container, false);
//        lv= (ListView) rootView.findViewById(R.id.list); //from home screen list view
        new getStudents().execute(); // it will execute your AsyncTask

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /////

    public class getStudents extends AsyncTask<Void,Void,Void> {
        protected void onPreExecute(){
            super.onPreExecute(); //it will use pre defined preExecute method in async task
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Fetching JSON.,."); // show what you want in the progress dialog
            progress.setCancelable(false); //progress dialog is not cancellable here
            progress.show();
        }

        protected Void doInBackground(Void...arg0){
            _config_HTTP_Handler hh = new _config_HTTP_Handler(); // object of _config_HTTP_Handler
            String jString = hh.makeHTTPCall(url); //calling makeHTTPCall method and string its response in a string
            Log.e(classtag, "Response from URL: " + jString);
            if (jString != null) {
                try {
                    JSONObject jObj = new JSONObject(jString); //our json data starts with the object
                    JSONArray students = jObj.getJSONArray("studentsinfo"); //fetch array from studentsinfo object
                    for (int i = 0; i < students.length(); i++) {
                        JSONObject student = students.getJSONObject(i); //get object from i index
                        String id=student.getString("id");   //save string from variable 'id' to string
                        String name=student.getString("name");
                        String email=student.getString("email");
                        String gender=student.getString("gender");

                        JSONObject phone=student.getJSONObject("phone"); //get object from phone
                        String mobile=phone.getString("mobile");  //save string from variable 'mobile' to string

                        HashMap<String, String> studentdata = new HashMap<>(); //create a hash map to set key and value pair

                        studentdata.put("id", id); //hash map will save key and its value
                        studentdata.put("name", name);
                        studentdata.put("email", email);
                        studentdata.put("gender",gender);
                        studentdata.put("mobile", mobile);
                        dataArrayList.add(studentdata); //now save all of the key value pairs to arraylist
                    }
                } catch (final JSONException e) {
                    Log.e(classtag, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplication(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show(); //show if you catch any exception with data
                        }
                    });
                }
            } else {
                Log.e(classtag, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {Toast.makeText(getActivity().getApplication(),
                            "Couldn't get json from server. Check internet connection!",
                            Toast.LENGTH_LONG).show();//show if you are unable to connect with the internet or if jString is null
                    }
                });
            }
            return null;
        }

        protected void onPostExecute(Void Result){
            super.onPostExecute(Result);
            if(progress.isShowing()){
                progress.dismiss();
            }
            ListAdapter adapter= new SimpleAdapter(
                    getActivity(),
                    dataArrayList,
                    R.layout.fragment_b_home_cek_nilai_siswa_smt2,
                    new String[]{"email","mobile"},
                    new int[]{R.id.siswa_nama_guru_cek_nilai_siswa_tab1_smt2,R.id.siswa_nama_mapel_cek_nilai_siswa_tab1_smt2});
            setListAdapter(adapter);
        }
    }

    public void onStart(){
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Toast.makeText(getActivity(), dataArrayList.get(pos).get("email"), Toast.LENGTH_SHORT).show();
            }
        });
    }

}