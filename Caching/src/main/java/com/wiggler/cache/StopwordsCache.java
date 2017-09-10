package com.wiggler.cache;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StopwordsCache implements Cache {
    private volatile List<String> stopwords = null;

    @Override
    public void load() throws Exception {
        stopwords = new ArrayList<>(Arrays.asList("the", "a", "if", "is", "not", "and", "or", "do"));
    }

    @Override
    public void freeze() {
        stopwords = Collections.unmodifiableList(stopwords);
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
