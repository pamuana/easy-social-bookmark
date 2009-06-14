package br.bookmark.util.textanalysis;

import br.bookmark.util.MetaDataVector;

public interface MetaDataExtractor {
    public MetaDataVector extractMetaData(String title, String body);
}
