LuceneSpellChecker
==================

Did you mean feature using Lucene supporting multiple distance measures and accuracy.


LuceneDYM dym = DYMFactory.buildLuceneDYM(DistanceMeasure.LevensteinDistance, 0.8f);
dym.getSuggestedQuery(queryWithSpellingMistakes));
