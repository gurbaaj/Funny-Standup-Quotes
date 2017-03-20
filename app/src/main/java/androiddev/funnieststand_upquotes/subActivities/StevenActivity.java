package androiddev.funnieststand_upquotes.subActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.StevenSwipeAdapter;

public class StevenActivity extends AppCompatActivity {
    ViewPager viewPager;
    StevenSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steven);
        viewPager = (ViewPager) findViewById(R.id.steven_viewPager);
        adapter = new StevenSwipeAdapter(this);
        viewPager.setAdapter(adapter);

    }
}
