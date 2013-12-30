package com.getnpk.spellchecker;

import com.getnpk.spellchecker.distance.LuceneDYM;
import com.getnpk.spellchecker.distance.DistanceMeasure;
import spellcheck.PhoneticSuggestion;

public class App {

    public static void main(String[] args) throws Exception {

        LuceneDYM dym = DYMFactory.buildLuceneDYM(DistanceMeasure.LevensteinDistance, 0.8f);

        PhoneticSuggestion pho = new PhoneticSuggestion();
        
        String query = "buziness and haspital and starage ficiliti";

       
        System.out.println("ORIGINAL: " + query);
        System.out.println("MODIFIED: " + dym.getSuggestedQuery(query));
      
        System.out.println("PHONETIC: " + pho.getSuggestedQuery(query));
    }
}
