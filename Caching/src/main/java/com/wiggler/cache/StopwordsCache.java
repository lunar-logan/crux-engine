package com.wiggler.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopwordsCache implements Cache {
    private final List<String> stopwords = new ArrayList<>(20);

    @Override
    public void load() throws Exception {
        stopwords.addAll(Arrays.asList("the", "a", "if", "is", "not", "and", "or", "do"));
    }

    public List<String> getStopwords() {
        return stopwords;
    }

    @Override
    public String toString() {
        return "StopwordsCache{" +
                "stopwords=" + stopwords +
                '}';
    }
}
