package com.getnpk.spellchecker.distance;

import org.apache.lucene.search.spell.NGramDistance;

/**
 *
 * @author nitinkp
 */
public class NGramDistanceLuceneDYM extends LuceneDYM {

    public NGramDistanceLuceneDYM(float accuracy) {
        super(accuracy);
        spellChecker.setStringDistance(new NGramDistance(2));
    }
}
