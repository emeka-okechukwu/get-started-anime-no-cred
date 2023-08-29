package com.emekaokechukwu.getstartedanime.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.emekaokechukwu.getstartedanime.model.Movie;

public class SortingAlgorithm {
    public static List<Movie> quickSortMoviesByRating(Collection<Movie> movies) {
        List<Movie> movieList = new ArrayList<>(movies);
        quickSort(movieList, 0, movieList.size() - 1);
        return movieList;
    }

    private static void quickSort(List<Movie> movies, int low, int high) {
        if (low < high) {
            int pi = partition(movies, low, high);
            quickSort(movies, low, pi - 1);
            quickSort(movies, pi + 1, high);
        }
    }

    private static int partition(List<Movie> movies, int low, int high) {
        double pivot = movies.get(high).getRating();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (movies.get(j).getRating() >= pivot) {
                i++;
                Collections.swap(movies, i, j);
            }
        }

        Collections.swap(movies, i + 1, high);
        return i + 1;
    }
}