package com.firebaseapp.firebaseapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistAdapter extends BaseAdapter {
    ArrayList<Artist> artists;
    Context context;

    public ArtistAdapter(ArrayList<Artist> artists, Context context) {
        this.artists = artists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Object getItem(int i) {
        return artists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = LayoutInflater.from(context).inflate(R.layout.artist_layout,null);
        TextView tvName = myView.findViewById(R.id.tvName);
        TextView tvGenre = myView.findViewById(R.id.tvGenre);

        tvName.setText(artists.get(i).getName());
        tvGenre.setText(artists.get(i).getGenre());

        return myView;
    }
}
