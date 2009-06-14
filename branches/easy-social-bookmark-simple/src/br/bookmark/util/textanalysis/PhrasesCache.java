package br.bookmark.util.textanalysis;

import java.io.IOException;

public interface PhrasesCache {
    public boolean isValidPhrase(String text) throws IOException;
}
