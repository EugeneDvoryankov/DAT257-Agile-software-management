package com.example.eurythmics.view.fragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.eurythmics.model.api.models.MovieService;
import com.example.eurythmics.R;
import com.example.eurythmics.model.api.Review.Review;
import com.example.eurythmics.model.api.Credentials;
import com.example.eurythmics.model.api.models.MovieModel;

import java.util.MissingResourceException;

public class RatedMovieDetailView extends Fragment {
    private ImageView poster, likeButton;

    private TextView title, category, releaseDate, durationTime,
            storyRating, charactersRating, scoreRating, sceneryRating, overallRating;

    private Button addRatingButton;

    private MovieModel chosenMovie;

    private MovieService ms;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_with_existing_rating_view, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null){
            MovieModel movieModel = bundle.getParcelable("ratedMovie");
            chosenMovie = movieModel.movieModel;
        } else {
            throw new MissingResourceException("No chosen transaction was sent with the fragment, hence fragment cannot be created", MovieModel.class.toString(), "CHOSEN_TRANSACTION" );
        }


        title = view.findViewById(R.id.title_detail_view);
        releaseDate = view.findViewById(R.id.release_date_detail_view);
        poster = view.findViewById(R.id.poster_detail_view);
        category = view.findViewById(R.id.category_detail_view);
        storyRating = view.findViewById(R.id.story_value_movie_with_existing);
        charactersRating = view.findViewById(R.id.chracters_value_movie_with_existing);
        scoreRating= view.findViewById(R.id.score_value_movie_with_existing);
        sceneryRating = view.findViewById(R.id.scenery_value_movie_with_existing);
        overallRating = view.findViewById(R.id.overall_rating_value_movie_with_existing);
        likeButton = view.findViewById(R.id.likeButton2);

        ms = MovieService.getMovieService();


        initLikeButton();

        initRatingView();


        getDataFromIntent();

        return view;

    }

    private void getDataFromIntent() {
        title.setText(chosenMovie.getTitle());
        releaseDate.setText(chosenMovie.getReleaseDate());
        Glide.with(this).load(Credentials.IMG_BASE_URL + chosenMovie.getPosterPath()).into(poster);
        category.setText(chosenMovie.getCategory());
    }

    private void initRatingView() {
        Review review = ms.getReview(chosenMovie.getMovie_id());
        storyRating.setText("" + review.getStoryRating());
        charactersRating.setText("" + review.getCharactersRating());
        scoreRating.setText("" + review.getScoreRating());
        sceneryRating.setText("" + review.getSceneryRating());
        overallRating.setText("" + review.getOverallRating());

    }

    private void initLikeButton() {
        if (ms.isFavorite(chosenMovie.getMovie_id())){
            likeButton.setImageResource(R.drawable.ic_favorite);
        }else {
            likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Check if movie is favorite
                //TODO Add movie to favorite

                // Add to favorites
                if (ms.isFavorite(chosenMovie.getMovie_id())){
                    likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    ms.deleteFavoriteMovie(chosenMovie.getMovie_id());
                }else {
                    likeButton.setImageResource(R.drawable.ic_favorite);
                    ms.addFavorite(chosenMovie.getMovie_id());
                }

            }
        });
    }
}