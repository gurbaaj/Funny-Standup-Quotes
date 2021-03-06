package androiddev.funnieststand_upquotes.viewpagerAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androiddev.funnieststand_upquotes.R;
import androiddev.funnieststand_upquotes.database.DatabaseAccess;

/**
 * Created by Gurpreet on 16-03-2017.
 */

public class CarlinSwipeAdapter extends PagerAdapter {
    List<String> carlinQuotes=new ArrayList<>();;
    int imgs[] = {R.drawable.carlin_1,R.drawable.carlin_2,R.drawable.carlin_3,R.drawable.carlin_4};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CarlinSwipeAdapter(Context ctx) {

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        carlinQuotes = databaseAccess.getCarlinQuotes();
        databaseAccess.close();
        this.ctx = ctx;

    }

    @Override
    public int getCount() {
        return carlinQuotes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.sub1_swipe_layout, container, false);
        TextView CarlinQuotes = (TextView) itemView.findViewById(R.id.carlinQuotes);
        final ImageView carlinImages = (ImageView) itemView.findViewById(R.id.ivCarlin);
        Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(),"fonts/whale.ttf");
        CarlinQuotes.setTypeface(custom_font);
        CarlinQuotes.setText(carlinQuotes.get(position));
        final Random rand = new Random();
        int diceRoll = rand.nextInt(4);
        carlinImages.setImageResource(imgs[diceRoll]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
