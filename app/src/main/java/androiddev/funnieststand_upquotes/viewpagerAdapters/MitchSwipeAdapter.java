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

public class MitchSwipeAdapter extends PagerAdapter {


    List<String> mitchQuotes=new ArrayList<>();;
    int mitchImages[] = {R.drawable.mitch_1,R.drawable.mitch_2,R.drawable.mitch_3,R.drawable.mitch_4};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public MitchSwipeAdapter(Context ctx){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        mitchQuotes = databaseAccess.getMitchQuotes();
        databaseAccess.close();
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return mitchQuotes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.mitch_swipe_layout,container,false);
        TextView mitchquotes = (TextView)itemView.findViewById(R.id.mitchQuotes);
        ImageView mitchImage = (ImageView)itemView.findViewById(R.id.ivMitch);
        Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(),"fonts/whale.ttf");
        mitchquotes.setTypeface(custom_font);
        mitchquotes.setText(mitchQuotes.get(position));
        final Random rand = new Random();
        int diceRoll = rand.nextInt(4);
        mitchImage.setImageResource(mitchImages[diceRoll]);
        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout)object);
    }
}
