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

public class BillSwipeAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context ctx;
    List<String> billQuotes = new ArrayList<>();
    int[] billImages = {R.drawable.bill_1, R.drawable.bill_2, R.drawable.bill_3, R.drawable.bill_4};

    public BillSwipeAdapter(Context ctx){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        billQuotes = databaseAccess.getBillQuotes();
        databaseAccess.close();
        this.ctx = ctx;
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
            ctx.startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ctx, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getCount() {
        return billQuotes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.bill_swipe_layout, container, false);
        TextView billQuote = (TextView) itemView.findViewById(R.id.tvBillQuotes);
        ImageView billImage = (ImageView) itemView.findViewById(R.id.ivBill);
        Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(),"fonts/sfns.ttf");
        billQuote.setTypeface(custom_font);
        final ImageButton ShareButton = (ImageButton)itemView.findViewById(R.id.btnShare);
        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "Button Clicked", Toast.LENGTH_SHORT).show();
                ShareButton.setVisibility(View.INVISIBLE);
                Bitmap mbitmap = screenShot(itemView);
                ShareButton.setVisibility(View.VISIBLE);
                saveScreenshot(mbitmap);
            }
        });
        billQuote.setText(billQuotes.get(position));
        Random random = new Random();
        int diceroll = random.nextInt(4);
        billImage.setImageResource(billImages[diceroll]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((RelativeLayout)object);
    }
}
