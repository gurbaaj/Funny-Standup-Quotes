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
 * Created by Gurpreet on 17-03-2017.
 */

public class StevenSwipeAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context ctx;
    int[] stevenImages = {R.drawable.steven_1, R.drawable.steven_2, R.drawable.steven_3, R.drawable.steven_4};
    List<String> stevenquotes = new ArrayList<>();

    public StevenSwipeAdapter(Context ctx) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        stevenquotes = databaseAccess.getStevenQuotes();
        databaseAccess.close();
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return stevenquotes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.steven_swipe_layout, container, false);
        TextView stevenQuote = (TextView) itemView.findViewById(R.id.stevenQuotes);
        ImageView stevenImage = (ImageView) itemView.findViewById(R.id.ivSteven);
        Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(), "fonts/whale.ttf");
        stevenQuote.setTypeface(custom_font);
        stevenQuote.setText(stevenquotes.get(position));
        Random random = new Random();
        int diceroll = random.nextInt(4);
        stevenImage.setImageResource(stevenImages[diceroll]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
