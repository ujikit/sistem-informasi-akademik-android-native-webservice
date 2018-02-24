package com.ujikit.man2yk;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by ujikit on 12/02/18.
 */

public class b_home extends AppCompatActivity {

    private static final String TAG = "b_home";
    private b_home_config1_fragmentPageAdapter_cekNilaiSiswa mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_home);
        Log.d(TAG, "onCreate: Starting.");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mSectionsPageAdapter = new b_home_config1_fragmentPageAdapter_cekNilaiSiswa(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        findViewById(R.id.tabs).bringToFront();
    }

    public void setupViewPager(ViewPager viewPager){
        b_home_config1_fragmentPageAdapter_cekNilaiSiswa adapter = new b_home_config1_fragmentPageAdapter_cekNilaiSiswa(getSupportFragmentManager());
        adapter.addFragment(new b_home_cek_nilai_siswa_smt1(), "TAB1");
        adapter.addFragment(new b_home_cek_nilai_siswa_smt2(), "TAB2");
        viewPager.setAdapter(adapter);
    }

}