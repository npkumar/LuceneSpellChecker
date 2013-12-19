package com.getnpk.spellchecker.distance;

import org.apache.lucene.search.spell.NGramDistance;

/**
 *
 * @author nitinkp
 */
public class BiGramDistanceLuceneDYM extends LuceneDYM {

    public BiGramDistanceLuceneDYM(float accuracy) {
        super(accuracy);
        spellChecker.setStringDistance(new NGramDistance(2));
    }
}
