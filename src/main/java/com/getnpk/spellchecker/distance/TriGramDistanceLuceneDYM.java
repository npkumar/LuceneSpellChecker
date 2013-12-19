package com.getnpk.spellchecker.distance;

import org.apache.lucene.search.spell.NGramDistance;

/**
 *
 * @author nitinkp
 */
public class TriGramDistanceLuceneDYM extends LuceneDYM {

    public TriGramDistanceLuceneDYM(float accuracy) {
        super(accuracy);
        spellChecker.setStringDistance(new NGramDistance(3));
    }
}
