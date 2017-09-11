package com.wiggler.crux.core;

import java.util.Objects;
import java.util.stream.Stream;

public class PreProcessor {
    private Tokenizer tokenizer;

    public PreProcessor(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Stream<String> process(String text) {
        Objects.requireNonNull(text);
        return tokenizer.tokenize(text);
    }
}
