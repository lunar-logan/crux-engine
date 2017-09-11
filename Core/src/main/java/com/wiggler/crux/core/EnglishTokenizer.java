package com.wiggler.crux.core;

import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

public class EnglishTokenizer implements Tokenizer {
    private static final String DEFAULT_DELIMITER = " ";

    public enum SettingType {
        DELIMITER,
    }

    private final Properties settings;

    public EnglishTokenizer(Properties settings) {
        this.settings = settings;
    }

    @Override
    public Stream<String> tokenize(String text) {
        Objects.requireNonNull(text);
        String delim = (String) settings.getOrDefault(SettingType.DELIMITER.name(), DEFAULT_DELIMITER);
        return Arrays.stream(text.split(delim));
    }
}
