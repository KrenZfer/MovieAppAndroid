package com.iakmovieapp.krenzfer.movieapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iakmovieapp.krenzfer.movieapp.Model.Video;
import com.iakmovieapp.krenzfer.movieapp.R;

/**
 * Created by krenzfer on 07/09/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Video[] videos;
    private Context context;

    private VideoHolderOnClickListener clickHanlder;

    public VideoAdapter(VideoHolderOnClickListener clickHanlder) {
        this.clickHanlder = clickHanlder;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_item_list, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video temp = videos[position];
        holder.videoName.setText(temp.getNameVideo());
    }

    @Override
    public int getItemCount() {
        if(videos == null){
            return 0;
        }else{
            return  videos.length;
        }
    }

    public interface VideoHolderOnClickListener {
        public void onClick(Video video);
    }

    public Video[] getVideos() {
        return videos;
    }

    public void setVideos(Video[] videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView videoName;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoName = (TextView) itemView.findViewById(R.id.video_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Video video = videos[position];
            clickHanlder.onClick(video);
        }
    }
}
