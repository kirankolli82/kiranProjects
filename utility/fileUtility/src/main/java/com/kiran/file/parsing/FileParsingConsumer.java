package com.kiran.file.parsing;

/**
 * Created by Kiran Kolli on 17-10-2016.
 */
public interface FileParsingConsumer {

    void accept(StringBuilder line);

    void finish();
}
