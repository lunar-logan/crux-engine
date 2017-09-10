package com.wiggler.crux.core;

import java.util.stream.Stream;

public interface AnalyzerComponent {
    Stream<Token> apply(Stream<Token> stream);
}
