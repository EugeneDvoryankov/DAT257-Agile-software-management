package com.example.eurythmics.view.fragments.fragments;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.eurythmics.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RatedSeriesCollectionFragment extends Fragment {

    private Button filterButton, sortButton;
    private Dialog filterDialog, sortDialog;

    private boolean filterIsActivated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rated_series_collection, container, false);

        filterButton = view.findViewById(R.id.series_filter_button);
        sortButton = view.findViewById(R.id.series_sort_button);

        initFilterDialog();
        initFilterButton();

        initSortDialog();
        initSortButton();
        return view;
    }

    private void initSortButton() {
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortDialog.show();
            }
        });
    }

    private void initSortDialog() {
        sortDialog = new Dialog(getActivity());
        sortDialog.setContentView(R.layout.sort_dialog);
        RadioGroup sortRadioGroup = sortDialog.findViewById(R.id.sort_radio_group);
        sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.newest_date_radio:
                        setDeActivatedColor(sortButton);
//                        ssf.sortBy(SortOption.NEWEST_DATE);
                        break;

                    case R.id.oldest_date_radio:
//                        ssf.sortBy(SortOption.OLDEST_DATE);
                        setActivatedColor(sortButton);
                        break;

                    case R.id.highestRating_radio:
//                        ssf.sortBy(SortOption.LARGEST_AMOUNT);
                        setActivatedColor(sortButton);
                        break;

                    case R.id.durationTime_radio:
//                        ssf.sortBy(SortOption.SMALLEST_AMOUNT);
                        setActivatedColor(sortButton);
                        break;
                }
//                updateList();
                sortDialog.cancel();
            }
        });
    }

    private void initFilterButton() {
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.show();
            }
        });
    }

    private void initFilterDialog() {
        filterDialog = new Dialog(getActivity());
        filterDialog.setContentView(R.layout.filter_dialog);
        RadioGroup filterRadio = filterDialog.findViewById(R.id.filter_radio_group);
        filterRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.action_radio:
                        filterIsActivated = false;
                        setDeActivatedColor(filterButton);
//                        ssf.setFilter(FilterOption.ALL_CATEGORIES);

                        break;
                    case R.id.adventure_radio:
                        filterIsActivated = true;
                        setActivatedColor(filterButton);
//                        ssf.setFilter(FilterOption.EXPENSE);
                        break;

                    case R.id.comedy_radio:
                        filterIsActivated = true;
                        setActivatedColor(filterButton);
//                        ssf.setFilter(FilterOption.INCOME);
                        break;
                }
                //updateList();
                filterDialog.cancel();
            }
        });

    }

    private void setActivatedColor(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.teal_200)));
    }

    private void setDeActivatedColor(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffd6d7d7")));
    }

}