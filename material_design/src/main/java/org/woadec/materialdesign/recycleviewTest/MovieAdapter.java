package org.woadec.materialdesign.recycleviewTest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.woadec.materialdesign.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements View.OnClickListener {


    private List<Movie> movieList;
    private int mPosition;

    private ItemClickListener listener;

    public MovieAdapter(List<Movie> movieList, final ItemClickListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        Log.e(getClass().getSimpleName(), "" + mPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView title, description, year;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.movie_title);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.year = (TextView) itemView.findViewById(R.id.year);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item_view_activity, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, viewHolder.getPosition(), movieList.get(viewHolder.getPosition()).getMovieLink());

            }
        });


        itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                listener.onLongPress(v, viewHolder.getPosition(), movieList.get(viewHolder.getPosition()).getMovieLink());
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie curretMovie = movieList.get(position);
        holder.title.setText(curretMovie.getMovieTitle());
        holder.description.setText(curretMovie.getDescription());
        holder.year.setText(curretMovie.getYear());
        this.mPosition = position;


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
