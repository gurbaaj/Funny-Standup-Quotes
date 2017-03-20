package androiddev.funnieststand_upquotes.subActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.LouieSwipeAdapter;

public class LouisActivity extends AppCompatActivity {
    ViewPager viewPager;
    LouieSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_louis);
        viewPager = (ViewPager) findViewById(R.id.louieViewPager);
        adapter = new LouieSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
