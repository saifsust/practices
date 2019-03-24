package org.woadec.uralems.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.woadec.uralems.R;
import org.woadec.uralems.listeners.HomeContentClickListener;
import org.woadec.uralems.models.Home;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<HomeRecycleViewAdapter.HomeViewHolder> {




    private final int FRONT_VIEW = 0;
    private final int NEXT_VIEW = 2;

    private Context context;
    private List<Home> homeList;
    private HomeContentClickListener clickListener;


    public HomeRecycleViewAdapter(Context context, List<Home> homeList, final HomeContentClickListener clickListener) {
        this.context = context;
        this.homeList = homeList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if(viewType ==FRONT_VIEW)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.front_layout_activity, parent, false);
            return new HomeViewHolder(view);

        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_main_activity, parent, false);
            return new HomeViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        Home curr = homeList.get(position);

        //'holder.title.setText(curr.getTitle());
        //holder.header.setText(curr.getHeader());
        //holder.description.setText(curr.getDescription());


    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);

       Home home = homeList.get(position);
       if(position==FRONT_VIEW) return FRONT_VIEW;
       else return  NEXT_VIEW;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {


        public ImageView icon, backgroundImage;
        public TextView header, title, description;
        public Button cancel, like, share, love;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.content_icon);
            backgroundImage = (ImageView) itemView.findViewById(R.id.content_main_image);

            header = (TextView) itemView.findViewById(R.id.content_header);
            title = (TextView) itemView.findViewById(R.id.content_title);
            description = (TextView) itemView.findViewById(R.id.news_title);

            cancel = (Button) itemView.findViewById(R.id.content_cancel);
            like = (Button) itemView.findViewById(R.id.content_like_button);
            share = (Button) itemView.findViewById(R.id.content_share);
            love = (Button) itemView.findViewById(R.id.content_love_button);


        }
    }
}
