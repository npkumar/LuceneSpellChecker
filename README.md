LuceneSpellChecker
==================

Did you mean feature using Lucene supporting multiple distance measures and accuracy.

Use target/SpellChecker-1.0-SNAPSHOT.jar

<blockquote>
LuceneDYM dym = DYMFactory.buildLuceneDYM(DistanceMeasure.LevensteinDistance, 0.8f);
dym.getSuggestedQuery(queryWithSpellingMistakes));
</blockquote>
