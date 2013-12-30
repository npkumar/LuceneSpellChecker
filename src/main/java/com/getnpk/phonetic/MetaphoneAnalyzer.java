package com.getnpk.phonetic;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.TokenStream;

/*
 * Replaces the token with metaphone encoding
 */
public class MetaphoneAnalyzer extends Analyzer {
    
    @Override
    public TokenStream tokenStream(String fieldName, Reader reader) {
        return new MetaphoneFilter(new LetterTokenizer(reader));
    }
    
}