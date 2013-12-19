package com.getnpk.spellchecker;

import com.getnpk.spellchecker.distance.LuceneDYM;
import com.getnpk.spellchecker.distance.BiGramDistanceLuceneDYM;
import com.getnpk.spellchecker.distance.TriGramDistanceLuceneDYM;
import com.getnpk.spellchecker.distance.LevensteinDistanceLuceneDYM;
import com.getnpk.spellchecker.distance.DistanceMeasure;
import com.getnpk.spellchecker.distance.JaroWinklerDistanceLuceneDYM;
import static com.getnpk.spellchecker.distance.DistanceMeasure.TriGramDistance;

/**
 *
 * @author nitinkp
 */
public class DYMFactory {
    
    private static LuceneDYM dym;
    
    public static LuceneDYM buildLuceneDYM(DistanceMeasure measure, float accuracy){
        
        switch(measure){
            case JaroWinklerDistance:
                dym = new JaroWinklerDistanceLuceneDYM(accuracy);
                break;
            case LevensteinDistance:
                dym = new LevensteinDistanceLuceneDYM(accuracy);
                break;
            case BiGramDistance:
                dym = new BiGramDistanceLuceneDYM(accuracy);
                break;
            case TriGramDistance:
                dym = new TriGramDistanceLuceneDYM(accuracy);
                break;
            case NGramDistance:
                dym = new TriGramDistanceLuceneDYM(accuracy);
                break;
            default:
                dym = new LevensteinDistanceLuceneDYM(accuracy);
                break;
        }
        return dym;
    }
}
