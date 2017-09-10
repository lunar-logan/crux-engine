package com.wiggler.crux.core;

import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Analyzer analyzer = new SimpleEnglishAnalyzer();
        analyzer.addComponent(new TokenizerComponent("[_ ]+"));
        String text = "this is _so_cool man";
        analyzer.analyze(text).forEach(System.out::println);
    }
}
