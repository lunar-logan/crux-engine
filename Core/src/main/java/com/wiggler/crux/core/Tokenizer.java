package com.wiggler.crux.core;

import java.util.stream.Stream;

public interface Tokenizer {
    Stream<String> tokenize(String text);
}
