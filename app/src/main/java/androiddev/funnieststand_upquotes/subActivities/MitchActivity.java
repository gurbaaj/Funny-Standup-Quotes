package androiddev.funnieststand_upquotes.subActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.MitchSwipeAdapter;

public class MitchActivity extends AppCompatActivity {

    ViewPager viewPager;
    MitchSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitch);
        viewPager = (ViewPager) findViewById(R.id.mitch_viewPager);
        adapter = new MitchSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
