package com.example.dead_mfcs_widget;


import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DeadRussianService {
    private static final Pattern PATTERN = Pattern.compile("(.*) —.* (\\d*)\\s.*\\+(\\d*)", Pattern.CASE_INSENSITIVE);

    public static Map<String, DeadRussians> getDeadRussians() throws IOException {
        Document doc = Jsoup.connect("https://index.minfin.com.ua/russian-invading/casualties/").get();
        doc.title();
        Elements newsHeadlines = doc.select("div.casualties");
        Log.i("PROCESSING", "GET russians...");
        Map<String, DeadRussians> deadRussiansMap = newsHeadlines.stream().map(headline -> headline.select("ul")).findFirst().get().stream()
                .map(elements -> elements.select("li"))
                .map(Elements::eachText)
                .flatMap(strings -> strings.stream().map(s -> s.split(",")))
                .flatMap(Arrays::stream)
                .filter(element -> element.matches(".*\\s\\d*\\s.*"))
                .map(PATTERN::matcher)
                .filter(Matcher::find)
                .map(e -> new DeadRussians(e.group(1), e.group(2), e.group(3)))
                .collect(Collectors.toMap(DeadRussians::getType, Function.identity()));


        return deadRussiansMap;
    }
}
