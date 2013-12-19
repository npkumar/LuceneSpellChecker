/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getnpk.spellchecker.distance;

import org.apache.lucene.search.spell.JaroWinklerDistance;

/**
 *
 * @author nitinkp
 */
public class JaroWinklerDistanceLuceneDYM extends LuceneDYM {

    public JaroWinklerDistanceLuceneDYM(float accuracy) {
      super(accuracy);
      spellChecker.setStringDistance(new JaroWinklerDistance());
    }

}
