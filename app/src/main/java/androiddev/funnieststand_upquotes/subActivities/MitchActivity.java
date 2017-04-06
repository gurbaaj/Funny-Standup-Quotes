package androiddev.funnieststand_upquotes.subActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.MitchSwipeAdapter;

public class MitchActivity extends AppCompatActivity {

    ViewPager viewPager;
    MitchSwipeAdapter adapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitch);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Mitch Hedberg");
        actionBar.setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.mitch_viewPager);
        final FloatingActionButton fabShare = (FloatingActionButton)findViewById(R.id.fab);
        final CoordinatorLayout Screen = (CoordinatorLayout)findViewById(R.id.shareScreen);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MitchActivity.this, "Starting Intent", Toast.LENGTH_SHORT).show();
                fabShare.setVisibility(View.GONE);
                Screen.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                Bitmap mBitmap = screenShot(Screen);
                Screen.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                fabShare.setVisibility(View.VISIBLE);
                saveScreenshot(mBitmap);
            }
        });
        adapter = new MitchSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void saveScreenshot(Bitmap bm) {
        ByteArrayOutputStream bao = null;
        File file = null;

        try {
            bao = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,100,bao);

            file = new File(Environment.getExternalStorageDirectory()+ File.separator + "Image.jpg");
            file.createNewFile();

            FileOutputStream fos  = new FileOutputStream(file);
            fos.write(bao.toByteArray());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MitchActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}
