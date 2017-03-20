package androiddev.funnieststand_upquotes.subActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.BillSwipeAdapter;

public class BillActivity extends AppCompatActivity {
    ViewPager viewPager;
    BillSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        viewPager = (ViewPager) findViewById(R.id.bill_viewPager);
        adapter = new BillSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
