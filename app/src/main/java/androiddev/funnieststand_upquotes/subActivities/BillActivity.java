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
import java.io.IOException;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.viewpagerAdapters.BillSwipeAdapter;

public class BillActivity extends AppCompatActivity {
    ViewPager viewPager;
    BillSwipeAdapter adapter;
    ActionBar actionBar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Bill Burr");
        actionBar.setDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        final CoordinatorLayout Screen = (CoordinatorLayout) findViewById(R.id.toShareScreen);
        viewPager = (ViewPager) findViewById(R.id.bill_viewPager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BillActivity.this, "Starting Intent", Toast.LENGTH_SHORT).show();
                fab.setVisibility(View.GONE);
                Screen.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                Bitmap mBitmap = screenShot(Screen);
                Screen.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                fab.setVisibility(View.VISIBLE);
                saveScreenShot(mBitmap);
            }
        });
        adapter = new BillSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void saveScreenShot(Bitmap bm) {
        ByteArrayOutputStream bao = null;
        File file = null;
        try {
            bao = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 50, bao);

            file = new File(Environment.getExternalStorageDirectory() + File.separator + "Image.jpg");
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bao.toByteArray());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //For Sharing the ScreenShot

        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        try {
            startActivity(Intent.createChooser(intent, "Share this Quote"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(BillActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }

    }
}
