package androiddev.funnieststand_upquotes.subActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.CarlinSwipeAdapter;

public class CarlinActivity extends AppCompatActivity {
    ViewPager viewPager;
    CarlinSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new CarlinSwipeAdapter(this);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
    }
}
