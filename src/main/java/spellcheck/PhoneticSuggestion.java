package spellcheck;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

import com.getnpk.phonetic.MetaphoneAnalyzer;
import com.getnpk.spellchecker.DYMFactory;
import com.getnpk.spellchecker.distance.DistanceMeasure;
import com.getnpk.spellchecker.distance.LuceneDYM;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class PhoneticSuggestion {

    Directory pdirectory;
    Analyzer analyzer;
    IndexWriter writer;
    Document doc;
    String metaphoneIndexDir = "C:\\Users\\nitinkp\\Desktop\\LuceneSuggestionsProject\\metadir";

    public PhoneticSuggestion() {

        try {
            pdirectory = FSDirectory.open(new File(metaphoneIndexDir));
            analyzer = new MetaphoneAnalyzer();
            File file = new File(metaphoneIndexDir);
            if (!file.exists()) {
                createIndex();
            }

        } catch (IOException ex) {
            Logger.getLogger(PhoneticSuggestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createIndex() throws IOException {

        //RAMDirectory directory = new RAMDirectory();
        //IndexWriter writer = new IndexWriter(directory, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);

        writer = new IndexWriter(pdirectory, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);

        File file = new File("C:\\Users\\nitinkp\\Desktop\\LuceneSuggestionsProject\\newdictionary.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String word;
        while ((word = reader.readLine()) != null) {
            if (word.length() > 5) {
                System.out.println(word.trim());
                doc = new Document();
                doc.add(new Field("contents",
                        word.trim(),
                        Field.Store.YES,
                        Field.Index.ANALYZED));
                writer.addDocument(doc);
            }
        }
        writer.close();
    }

    public String suggestWord(String word) throws CorruptIndexException, IOException, ParseException {

        IndexSearcher searcher = new IndexSearcher(pdirectory);

        Query query = new QueryParser(Version.LUCENE_30, "contents", analyzer).parse(word);

        //find top n hits for query
        TopDocs hits = searcher.search(query, 1);

        if (hits.totalHits > 0) {
            int docID = hits.scoreDocs[0].doc;
            doc = searcher.doc(docID);
            //System.out.println(hits.totalHits);
            searcher.close();
            return (doc.get("contents"));
        } else {
            searcher.close();
            return word;
        }
    }

    public String getSuggestedQuery(String query) {

        LuceneDYM dym = DYMFactory.buildLuceneDYM(DistanceMeasure.LevensteinDistance, 0.9f);
        StringBuilder sb = new StringBuilder();
        for (String word : query.split(" ")) {

            word = word.trim();
            
            try {
                if (!("NULL".equals(dym.ifWordExists(word)))) {
                    sb.append(dym.getWordSuggestion(word));
                    sb.append(" ");
                } else {
                    sb.append(suggestWord(word));
                    sb.append(" ");
                }
            } catch (Exception ex) {
                Logger.getLogger(PhoneticSuggestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();

    }
}
