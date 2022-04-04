package com.example.androidcsi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.androidcsi.fragments.CSIFragment;
import com.example.androidcsi.fragments.highRatingFragment;
import com.example.androidcsi.fragments.kpeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 pager;
    private Toolbar toolbar;
    private TabLayout tabs;
    private MyFragmentStateAdapter fragmentStateAdapter;
    private TabLayoutMediator tabMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Высокие оценки");
        setSupportActionBar(toolbar);
        tabs = findViewById(R.id.tabs);

        pager = findViewById(R.id.pager);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        toolbar.setTitle("CSI");
                        //toolbar.setSubtitle("Расчёт CSI");
                        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue));
                        tabs.setTabTextColors(
                                getResources().getColor(R.color.siteGray),
                                getResources().getColor(R.color.blue));
                        break;
                    case 1:
                        toolbar.setTitle("Высокие оценки");
                        //toolbar.setSubtitle("Расчёт количества высоких оценок");
                        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.green_dark));
                        tabs.setTabTextColors(
                                getResources().getColor(R.color.siteGray),
                                getResources().getColor(R.color.green_dark));
                        break;
                    case 2:
                        toolbar.setTitle("КПЭ");
                        //toolbar.setSubtitle("Расчёт КПЭ");
                        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.orange_darkly));
                        tabs.setTabTextColors(
                                getResources().getColor(R.color.siteGray),
                                getResources().getColor(R.color.orange_darkly));
                        break;
                }
            }
        });
        fragmentStateAdapter = new MyFragmentStateAdapter(getSupportFragmentManager(), getLifecycle());
        fragmentStateAdapter.addFragment(new CSIFragment());
        fragmentStateAdapter.addFragment(new highRatingFragment());
        fragmentStateAdapter.addFragment(new kpeFragment());
        pager.setAdapter(fragmentStateAdapter);
        pager.setCurrentItem(1);


        tabMediator = new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("CSI");
                        break;
                    case 1:
                        tab.setText("Высокие оценки");
                        break;
                    case 2:
                        tab.setText("КПЭ");
                        break;
                }
            }
        });
        tabMediator.attach();
    }
}