package org.woadec.materialdesign.cardviewTest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.woadec.materialdesign.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {


    private List<Album> albumList;
    private ClickListener clickListener;
    private Context context;


    public AlbumAdapter(List<Album> albumList, ClickListener clickListener, Context context) {
        this.albumList = albumList;
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_activity, parent, false);
        AlbumViewHolder holder = new AlbumViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumViewHolder holder, final int position) {
        Album album = albumList.get(position);
        holder.albumTitle.setText(album.getName());
        holder.numOfSongs.setText(Integer.toString(album.getNumOfSongs()));


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(holder.resources,album.getThumnail(), options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,200, 200);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;


        Bitmap bitmap = BitmapFactory.decodeResource(holder.resources, album.getThumnail(), options);
        holder.thumbnai.setImageBitmap(bitmap);


        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickMenu(holder.action, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        public TextView albumTitle, numOfSongs;
        public ImageView thumbnai, action;
        public Resources resources;

        public AlbumViewHolder(@NonNull View view) {
            super(view);
            albumTitle = (TextView) view.findViewById(R.id.album_title);
            numOfSongs = (TextView) view.findViewById(R.id.song_count);
            thumbnai = (ImageView) view.findViewById(R.id.thumbnai);
            action = (ImageView) view.findViewById(R.id.overflow_image);

            this.resources = view.getResources();

        }
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height >>1;
            final int halfWidth = width >>1;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize <<=1;
            }
        }

        return inSampleSize;
    }

}

