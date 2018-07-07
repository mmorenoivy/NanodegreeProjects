package com.example.android.movieposters.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movieposters.R;
import com.example.android.movieposters.model.TrailerResult;

import java.util.List;


public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<TrailerResult> mTrailerList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public TrailerAdapter(Context context, List<TrailerResult> trailers) {
        this.mContext = context;
        this.mTrailerList = trailers;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder trailerViewHolder, final int position) {
        //TrailerResult trailerItems = mTrailerList.get(position);
        trailerViewHolder.title.setText(mTrailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (null != mTrailerList ? mTrailerList.size() : 0);
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        protected ImageView imageView;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.showTitle);
            this.imageView = itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int pos = getAdapterPosition();
                                                if(pos!=RecyclerView.NO_POSITION)
                                                {
                                                    TrailerResult clickedDataItem = mTrailerList.get(pos);
                                                    String videoId = mTrailerList.get(pos).getKey();
                                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+videoId));
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.putExtra("VIDEO_ID", videoId);
                                                    mContext.startActivity(intent);

                                                    Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
            );

        }
    }


}
