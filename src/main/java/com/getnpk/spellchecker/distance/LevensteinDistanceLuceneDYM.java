/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getnpk.spellchecker.distance;

import org.apache.lucene.search.spell.LevensteinDistance;

/**
 *
 * @author nitinkp
 */
public class LevensteinDistanceLuceneDYM extends LuceneDYM {

    public LevensteinDistanceLuceneDYM(float accuracy) {
        super(accuracy);
        spellChecker.setStringDistance(new LevensteinDistance());
    }
}
