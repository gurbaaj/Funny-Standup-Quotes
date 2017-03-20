package androiddev.funnieststand_upquotes;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Gurpreet on 16-03-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    int[] comicImages;
    String[] comedians;

    public RecyclerAdapter(String[] comedians, int[] comicImages) {

        this.comedians = comedians;
        this.comicImages = comicImages;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.comedian.setText(comedians[position]);
        holder.comicImage.setImageResource(comicImages[position]);
    }

    @Override
    public int getItemCount() {
        return comedians.length;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView comedian;
        ImageView comicImage;


        public RecyclerViewHolder(View view) {
            super(view);
            comicImage = (ImageView) view.findViewById(R.id.comicImage);
            comedian = (TextView) view.findViewById(R.id.comedianName);
        }

    }

}
