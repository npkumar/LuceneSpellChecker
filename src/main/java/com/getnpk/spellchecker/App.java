package com.getnpk.spellchecker;

import com.getnpk.spellchecker.distance.LuceneDYM;
import com.getnpk.spellchecker.distance.DistanceMeasure;

public class App {

    public static void main(String[] args) throws Exception {

        LuceneDYM dym = DYMFactory.buildLuceneDYM(DistanceMeasure.LevensteinDistance, 0.8f);

        String query = "hateroplassic buziness and haspital with a cld storege faciliti";

        System.out.println("ORIGINAL: " + query);
        System.out.println("MODIFIED: " + dym.getSuggestedQuery(query));
    }
}
