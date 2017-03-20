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
 * Created by Gurpreet on 20-03-2017.
 */

public class LouieSwipeAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context ctx;
    int[] louieImages = {R.drawable.louie_1, R.drawable.louie_2, R.drawable.louie_3, R.drawable.louie_4};
    List<String> louieQuotes = new ArrayList<>();

    public LouieSwipeAdapter(Context ctx) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        louieQuotes =databaseAccess.getLouieQuotes();
        databaseAccess.close();
        this.ctx = ctx;
    }

    public Bitmap screenShot(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void shareScreenShot(Bitmap bitmap){

        ByteArrayOutputStream bao;
        File file = null;

        try{
            bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bao);
            file = new File(Environment.getExternalStorageDirectory() + File.separator + "NewImage.jpg");
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bao.toByteArray());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(Intent.EXTRA_SUBJECT,"");
        intent.putExtra(Intent.EXTRA_TEXT,"");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        try{
            ctx.startActivity(Intent.createChooser(intent,"Share Quote"));
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ctx,"No App Avaiable",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public int getCount() {
        return louieQuotes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.louis_swipe_layout, container, false);
        ImageView louieImage = (ImageView) itemView.findViewById(R.id.ivLouie);
        TextView louieQuote = (TextView) itemView.findViewById(R.id.tvLouieQuotes);
        Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(),"fonts/sfns.ttf");
        final ImageButton shareQuote = (ImageButton)itemView.findViewById(R.id.btnShare);
        shareQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareQuote.setVisibility(View.INVISIBLE);
                Bitmap mbitmap = screenShot(itemView);
                shareQuote.setVisibility(View.VISIBLE);
                shareScreenShot(mbitmap);
            }
        });
        louieQuote.setTypeface(custom_font);
        louieQuote.setText(louieQuotes.get(position));
        Random random = new Random();
        int diceroll = random.nextInt(4);
        louieImage.setImageResource(louieImages[diceroll]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
