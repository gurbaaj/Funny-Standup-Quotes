package androiddev.funnieststand_upquotes;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androiddev.funnieststand_upquotes.subActivities.BillActivity;
import androiddev.funnieststand_upquotes.subActivities.JimmyActivity;
import androiddev.funnieststand_upquotes.subActivities.LouisActivity;
import androiddev.funnieststand_upquotes.subActivities.MitchActivity;
import androiddev.funnieststand_upquotes.subActivities.StevenActivity;
import androiddev.funnieststand_upquotes.subActivities.CarlinActivity;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    int comicImages[] = {R.drawable.carlin, R.drawable.mitch, R.drawable.bill, R.drawable.steven, R.drawable.jimmy, R.drawable.louie};
    String[] comedian = {"George Carlin", "Mitch Hedberg", "Bill Burr", "Steven Wright"
            , "Jimmy Carr", "Louis CK"};

    public static interface ClickListener {
        public void onClick(View view, int position);
    }


    public abstract class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(comedian, comicImages, this);

        //MediaPlayer Declaration

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.click_one);
                        mp.start();
                        startActivity(new Intent(MainActivity.this, CarlinActivity.class));
                        break;
                    case 1:
                        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.click_one);
                        mediaPlayer.start();
                        startActivity(new Intent(MainActivity.this, MitchActivity.class));
                        break;
                    case 2:
                        MediaPlayer mp1 = MediaPlayer.create(MainActivity.this, R.raw.click_one);
                        mp1.start();
                        startActivity(new Intent(MainActivity.this, BillActivity.class));
                        break;
                    case 3:
                        MediaPlayer mp2 = MediaPlayer.create(MainActivity.this,R.raw.click_one);
                        mp2.start();
                        startActivity(new Intent(MainActivity.this, StevenActivity.class));
                        break;
                    case 4:
                        MediaPlayer mp3 = MediaPlayer.create(MainActivity.this,R.raw.click_one);
                        mp3.start();
                        startActivity(new Intent(MainActivity.this, JimmyActivity.class));
                        break;
                    case 5:
                        MediaPlayer mp4 = MediaPlayer.create(MainActivity.this,R.raw.click_one);
                        mp4.start();
                        startActivity(new Intent(MainActivity.this, LouisActivity.class));
                        break;
                }
            }
        }) {
        });

    }
}
