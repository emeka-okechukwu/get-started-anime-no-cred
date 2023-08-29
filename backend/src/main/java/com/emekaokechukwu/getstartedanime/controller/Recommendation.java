package com.emekaokechukwu.getstartedanime.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emekaokechukwu.getstartedanime.model.Movie;
import com.emekaokechukwu.getstartedanime.util.SortingAlgorithm;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Recommendation {

    @Value("${openai.apiKey}")
    private String openAiApiKey;

    @Value("${cohere.apiKey}")
    private static String cohereApiKey;

    @Value("${tmdb.apiKey}")
    private String tmdbApiKey;

    private final Map<Integer, Movie> movieMap = new HashMap<>();
    private static final Map<String, Integer> genreMap = new HashMap<>();

    static {
        genreMap.put("Action", 28);
        genreMap.put("Adventure", 12);
        genreMap.put("Comedy", 35);
        genreMap.put("Crime", 80);
        genreMap.put("Documentary", 99);
        genreMap.put("Drama", 18);
        genreMap.put("Family", 10751);
        genreMap.put("Fantasy", 14);
        genreMap.put("History", 36);
        genreMap.put("Horror", 27);
        genreMap.put("Music", 10402);
        genreMap.put("Mystery", 9648);
        genreMap.put("Romance", 10749);
        genreMap.put("Science Fiction", 878);
        genreMap.put("Thriller", 53);
        genreMap.put("TV Movie", 10770);
        genreMap.put("War", 10752);
        genreMap.put("Western", 37);
    }

    @PostMapping("/recommend")
    public ResponseEntity<List<Movie>> recommend(@RequestBody Map<String, String> requestBody) {
        String movies = requestBody.get("input");
        String genre = getGenreFromAI(movies);
        getMovies(genre);
        List<Movie> sortedMovies = SortingAlgorithm.quickSortMoviesByRating(movieMap.values());
        return ResponseEntity.ok(sortedMovies);
    }

    public static String getGenreFromAI(String movies) {
        HttpClient client = HttpClient.newHttpClient();

        String prompt = "My favorite movies are " + movies +
                ". Please provide a single output genre from the list: " +
                "Action, Adventure, Comedy, Crime, Documentary, Drama, Family, Fantasy, " +
                "History, Horror, Music, Mystery, Romance, Science Fiction, Thriller, " +
                "TV Movie, War, Western. I want you to only reply with the genre, and nothing else.";

        String requestBody = "{" +
                "\"model\": \"command\"," +
                "\"prompt\": \"" + prompt + "\"," +
                "\"max_tokens\": 300," +
                "\"temperature\": 0.9," +
                "\"k\": 0," +
                "\"stop_sequences\": []," +
                "\"return_likelihoods\": \"NONE\"" +
                "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.cohere.ai/v1/generate"))
                .header("Authorization", "BEARER " + cohereApiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                return extractGenre(responseBody).strip();
            } else {
                System.out.println("Request failed with status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String extractGenre(String responseBody) {
        int startIndex = responseBody.indexOf("\"text\":") + 8;
        int endIndex = responseBody.indexOf("\"}", startIndex);
        return responseBody.substring(startIndex, endIndex);
    }

    public void getMovies(String genre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + tmdbApiKey + "&with_genres=" + genreMap.get(genre) + ", 16&with_original_language=ja";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
        for (Map<String, Object> result : results) {
            int id = (int) result.get("id");
            String title = (String) result.get("title");
            String synopsis = (String) result.get("overview");
            String releaseDate = (String) result.get("release_date");
            String year = releaseDate.substring(0, 4);
            List<String> genreNames = getGenres(id);
            List<String> castNames = getCast(id);
            double rating = ((Number) result.get("vote_average")).doubleValue();
            int runtime = getRuntime(id);
            String posterImage = (String) result.get("poster_path");
            String backdropImage = (String) result.get("backdrop_path");
            String link = "https://www.themoviedb.org/movie/" + id;
            Movie movie = new Movie(id, title, synopsis, year, genreNames, castNames, rating, runtime, posterImage, backdropImage, link);
            movieMap.put(id, movie);
        }
    }

    private List<String> getGenres(int id) {
        RestTemplate restTemplate = new RestTemplate();
        String genresUrl = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + tmdbApiKey + "&language=en-US";
        Map<String, Object> genresResponse = restTemplate.getForObject(genresUrl, Map.class);
        List<Map<String, Object>> genres = (List<Map<String, Object>>) genresResponse.get("genres");
        List<String> genreNames = new ArrayList<>();
        for (Map<String, Object> genreMap : genres) {
            String genreName = (String) genreMap.get("name");
            genreNames.add(genreName);
        }
        return genreNames;
    }

    private List<String> getCast(int id) {
        RestTemplate restTemplate = new RestTemplate();
        String creditsUrl = "https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=" + tmdbApiKey;
        Map<String, Object> creditsResponse = restTemplate.getForObject(creditsUrl, Map.class);
        List<Map<String, Object>> cast = (List<Map<String, Object>>) creditsResponse.get("cast");
        List<String> castNames = new ArrayList<>();
        for (int i = 0; i < 3 && i < cast.size(); i++) {
            Map<String, Object> castMember = cast.get(i);
            String castName = (String) castMember.get("name");
            castNames.add(castName);
        }
        return castNames;
    }

    public int getRuntime(int movieID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + tmdbApiKey;
        Map response = restTemplate.getForObject(url, Map.class);
        assert response != null;
        return (int) response.get("runtime");
    }

    public String getGenreFromOpenAI(String movies) {
        OpenAiService service = new OpenAiService(openAiApiKey);

        String prompt = "My favorite movies are " + movies + ", Please provide a single output genre from the list: " +
                "Action, Adventure, Comedy, Crime, Documentary, Drama, Family, Fantasy, History, Horror, Music, Mystery, Romance, Science Fiction, Thriller, TV Movie, War, Western.\n\n" +
                "I want you to only reply with the genre, and nothing else.";

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(
                ChatMessageRole.USER.value(), prompt);
        messages.add(systemMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .maxTokens(250)
                .build();

        List<ChatCompletionChoice> choices = service
                .createChatCompletion(chatCompletionRequest).getChoices();

        if (choices == null || choices.isEmpty()) {
            return "No response";
        }

        String generatedGenre = choices.get(0).getMessage().getContent();

        String[] validGenres = {
                "Action", "Adventure", "Comedy", "Crime", "Documentary",
                "Drama", "Family", "Fantasy", "History", "Horror",
                "Music", "Mystery", "Romance", "Science Fiction",
                "Thriller", "TV Movie", "War", "Western"
        };

        for (String genre : validGenres) {
            if (generatedGenre.equals(genre)) {
                return genre;
            }
        }

        return "";
    }
}