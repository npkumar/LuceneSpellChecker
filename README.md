LuceneSpellChecker
==================

* Did you mean feature using Lucene supporting multiple distance measures and accuracy.
* Phonetic Search using Lucene.

Example usage:

```
        
        LuceneDYM dym = DYMFactory.buildLuceneDYM(DistanceMeasure.LevensteinDistance, 0.8f);

        PhoneticSuggestion pho = new PhoneticSuggestion();
        
        String query = "buziness and haspital and starage ficiliti";

        System.out.println("ORIGINAL: " + query);
        System.out.println("MODIFIED: " + dym.getSuggestedQuery(query));
        System.out.println("PHONETIC: " + pho.getSuggestedQuery(query));
        
```
