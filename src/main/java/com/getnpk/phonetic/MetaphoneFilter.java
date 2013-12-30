package com.getnpk.phonetic;

import java.io.IOException;

import org.apache.commons.codec.language.Metaphone;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

/*
 * Replaces the token with metaphone encoding
 */
public class MetaphoneFilter extends TokenFilter {

	public static final String METAPHONE = "METAPHONE";
	
	private TermAttribute termAttribute;
	private TypeAttribute typeAttribute;
	
        private Metaphone metaphone = new Metaphone();
        
	public MetaphoneFilter(TokenStream input) {
		super(input);
		termAttribute = addAttribute(TermAttribute.class);
		typeAttribute = addAttribute(TypeAttribute.class);
	}
	
	public boolean incrementToken() throws IOException {
		if (!input.incrementToken())
			return false;
		
		String encoded;
		encoded = metaphone.encode(termAttribute.term());
		
		//override with encoded text
		termAttribute.setTermBuffer(encoded);
		//set the token type as METAPHONE
		typeAttribute.setType(METAPHONE);
		return true;
	}
	
}