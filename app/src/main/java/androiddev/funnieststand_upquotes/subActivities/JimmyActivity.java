package androiddev.funnieststand_upquotes.subActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.JimmySwipeAdapter;

public class JimmyActivity extends AppCompatActivity {
    ViewPager viewPager;
    JimmySwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jimmy);
        viewPager = (ViewPager) findViewById(R.id.jimmy_viewPager);
        adapter = new JimmySwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
