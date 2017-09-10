package com.wiggler.crux.core;

import java.util.stream.Stream;

public interface Analyzer {
    Stream<Token> analyze(String text);

    void addComponent(AnalyzerComponent component);

    void removeComponent(AnalyzerComponent component);
}
