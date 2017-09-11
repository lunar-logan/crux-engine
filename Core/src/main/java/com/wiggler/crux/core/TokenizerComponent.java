package com.wiggler.crux.core;

import java.util.stream.Stream;

public class TokenizerComponent implements AnalyzerComponent {
    private final String regex;

    public TokenizerComponent(String regex) {
        this.regex = regex;
    }

    @Override
    public Stream<Token> apply(Stream<Token> stream) {
        return stream.peek(token -> {
            String val = token.getValue();
            if (val != null) {
                String[] words = val.split(regex);
                int wordIndex = 0;
                for (String word : words) {
                    token.getChildren().add(new Token(word, wordIndex++));
                }
            }
        });
    }
}
