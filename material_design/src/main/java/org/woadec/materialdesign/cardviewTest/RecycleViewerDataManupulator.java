package org.woadec.materialdesign.cardviewTest;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import org.woadec.materialdesign.R;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewerDataManupulator implements Runnable {


    private RecyclerView recyclerView;
    private List<Album> albumList;
    private Context context;

    public RecycleViewerDataManupulator(RecyclerView recyclerView, List<Album> albumList, Context context) {
        this.recyclerView = recyclerView;
        this.albumList = albumList;
        this.context = context;
    }

    @Override
    public void run() {

        synchronized (recyclerView){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            AlbumAdapter albumAdapter = new AlbumAdapter(albumList, new ClickListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public void onLongClick(View view, int position) {

                }

                @Override
                public void onClickMenu(View view, int position) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.menu_album, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenuListener());

                    popupMenu.show();


                }
            },context);
            recyclerView.setAdapter(albumAdapter);

        }

    }


    private class PopupMenuListener implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }
    }
}
