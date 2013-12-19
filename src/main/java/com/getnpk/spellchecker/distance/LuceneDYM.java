package com.getnpk.spellchecker.distance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author nitinkp
 */
public class LuceneDYM {

    protected SpellChecker spellChecker;
    protected File dir;
    protected Directory directory;
    protected PlainTextDictionary plainTextDictionary;
    private java.util.Properties prop;
    
    public LuceneDYM(float accuracy) {
        
        prop = new java.util.Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        
        try {
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(LuceneDYM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dir = new File(prop.getProperty("indexPath"));
        try {
            plainTextDictionary = new PlainTextDictionary(new File(prop.getProperty("dictionaryPath")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LuceneDYM.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            directory = FSDirectory.open(dir);

            this.spellChecker = getSpellCheckerInstance();
            this.spellChecker.setAccuracy(accuracy);
        } catch (Exception ex) {
            Logger.getLogger(LuceneDYM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SpellChecker getSpellCheckerInstance() throws Exception {

        SpellChecker spellChecker = new SpellChecker(directory);

        if (dir.listFiles().length > 0) {
            System.out.println("Dictionary Index exists!");
        } else {
            spellChecker.indexDictionary(
                    plainTextDictionary, 300 //mergefactor
                    , 100 //max ram to use
                    );
        }
        return spellChecker;
    }

    public String getWordSuggestion(String word) throws Exception {

        String wordForSuggestions = word;

        int suggestionsNumber = 5;

        if (spellChecker.exist(wordForSuggestions)) {
            return word;
        }

        String[] suggestions = spellChecker.suggestSimilar(wordForSuggestions, suggestionsNumber);

        if (suggestions != null && suggestions.length > 0) {
//	    for (String newword : suggestions)
//              System.out.println("Did you mean:" + newword);
            return suggestions[0];
        } else {
//	    System.out.println("No suggestions found for word:"+wordForSuggestions);
            return word;
        }
    }

    public String getSuggestedQuery(String query) {

        StringBuilder sb = new StringBuilder();
        for (String word : query.split(" ")) {
            try {
                sb.append(getWordSuggestion(word));
                sb.append(" ");
            } catch (Exception ex) {
                Logger.getLogger(LuceneDYM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
    }
}
