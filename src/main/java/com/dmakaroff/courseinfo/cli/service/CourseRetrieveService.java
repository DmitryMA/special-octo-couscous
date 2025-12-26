package com.dmakaroff.courseinfo.cli.service;

import com.dmakaroff.courseinfo.cli.PluralsightCourse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrieveService {
    private static final String PS_URL = "https://app.pluralsight.com/profile/data/author/%s/all-content";
    private static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();


    public List<PluralsightCourse> getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URL.formatted(authorId)))
                .GET()
                .build();

        try {
            HttpResponse <String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch (response.statusCode()) {
                case 200 -> null;
                case 404 -> List.of();
                default -> throw new RuntimeException("Pluralsight API failed with status code " + response.statusCode());
            };
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Could not call API", ex);
        }
    }
}
