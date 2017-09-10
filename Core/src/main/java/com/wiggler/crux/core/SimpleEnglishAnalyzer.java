package com.wiggler.crux.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SimpleEnglishAnalyzer implements Analyzer {
    private final List<AnalyzerComponent> analyzerComponents = new ArrayList<>();

    @Override
    public Stream<Token> analyze(String text) {
        Stream<Token> tokenStream = Arrays.stream(new Token[]{new Token(text, 0)});
        for (AnalyzerComponent analyzerComponent : analyzerComponents) {
            tokenStream = analyzerComponent.apply(tokenStream);
        }
        return tokenStream;
    }

    @Override
    public void addComponent(AnalyzerComponent component) {
        analyzerComponents.add(component);
    }

    @Override
    public void removeComponent(AnalyzerComponent component) {
        analyzerComponents.remove(component);
    }
}
