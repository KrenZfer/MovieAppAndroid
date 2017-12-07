package com.iakmovieapp.krenzfer.movieapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iakmovieapp.krenzfer.movieapp.Model.MovieMini;
import com.iakmovieapp.krenzfer.movieapp.R;
import com.iakmovieapp.krenzfer.movieapp.Utils.NetworkUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by krenzfer on 03/09/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private MovieMini[] movieMinis;

    private Context context;

    private final MoviePosteronClickHandler movieClickHandler;

    public MovieAdapter(MoviePosteronClickHandler clickHandler){
        movieClickHandler = clickHandler;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.image_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Context vcontext = holder.imgThumbnail.getContext();
        MovieMini currentMovie = movieMinis[position];
        String thumbnailImage = currentMovie.getPosterPath();
        String rating = String.valueOf(currentMovie.getVote_average());
        String path = NetworkUtils.TMDB_IMAGE_CONFIGURATION_URL+thumbnailImage;
        Picasso.with(vcontext)
                .load(path)
                .placeholder(R.mipmap.broken_image)
                .into(holder.imgThumbnail);
        holder.textRating.setText(rating+"/10");
    }

    @Override
    public int getItemCount() {
        if(null == movieMinis){
            return 0;
        }else{
            return movieMinis.length;
        }
    }

    public interface MoviePosteronClickHandler{
        void onClick(MovieMini movie);
    }

    public MovieMini[] getMovieMinis() {
        return movieMinis;
    }

    public void setMovieMinis(MovieMini[] _moviesMinis) {
        this.movieMinis = _moviesMinis;
        this.notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView imgThumbnail;
        public final TextView textRating;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
            textRating = (TextView) itemView.findViewById(R.id.ratingText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            MovieMini movie = movieMinis[adapterPosition];
            movieClickHandler.onClick(movie);
        }
    }
}
