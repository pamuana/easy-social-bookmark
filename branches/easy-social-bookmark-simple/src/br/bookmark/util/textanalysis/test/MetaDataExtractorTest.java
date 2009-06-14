package br.bookmark.util.textanalysis.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import br.bookmark.util.MetaDataVector;
import br.bookmark.util.tagcloud.FontSizeComputationStrategy;
import br.bookmark.util.tagcloud.TagCloud;
import br.bookmark.util.tagcloud.VisualizeTagCloudDecorator;
import br.bookmark.util.tagcloud.impl.HTMLTagCloudDecorator;
import br.bookmark.util.tagcloud.impl.LinearFontSizeComputationStrategy;
import br.bookmark.util.tagcloud.impl.TagCloudImpl;
import br.bookmark.util.textanalysis.MetaDataExtractor;
import br.bookmark.util.textanalysis.impl.SimpleBiTermStopWordStemmerMetaDataExtractor;
import br.bookmark.util.textanalysis.impl.SimpleMetaDataExtractor;
import br.bookmark.util.textanalysis.impl.SimpleStopWordMetaDataExtractor;
import br.bookmark.util.textanalysis.impl.SimpleStopWordStemmerMetaDataExtractor;

import junit.framework.TestCase;


public class MetaDataExtractorTest extends TestCase {
    private static final String title = "Collective Intelligence and Web2.0";
    private static final String body = "Web2.0 is all about connecting users to users, " +
    "inviting users to participate and applying their collective " +
    "intelligence to improve the application. Collective intelligence " +
    "enhances the user experience";
    private static final int numSizes = 3;
    private static final String fontPrefix = "font-size: ";

    public void testSimpleMetaDataExtractor() throws Exception {
        SimpleMetaDataExtractor ex = new SimpleMetaDataExtractor();
        String fileName = "simpleExtractorChap3.html";
        generateTagCloud(ex,fileName);
    }
    
    public void testSimpleStopMetaDataExtractor() throws Exception {
        SimpleMetaDataExtractor ex = new SimpleStopWordMetaDataExtractor();
        String fileName = "simpleStopExtractorChap3.html";
        generateTagCloud(ex,fileName);
    }
    
    public void testSimpleStopStemmerMetaDataExtractor() throws Exception {
        SimpleMetaDataExtractor ex = new SimpleStopWordStemmerMetaDataExtractor();
        String fileName = "simpleStopCannonicalExtractorChap3.html";
        generateTagCloud(ex,fileName);
    }
    
    public void testSimpleBiTermStopStemmerMetaDataExtractor() throws Exception {
        SimpleMetaDataExtractor ex = new SimpleBiTermStopWordStemmerMetaDataExtractor();
        String fileName = "simpleBiTermStopCannonicalExtractorChap3.html";
        generateTagCloud(ex,fileName);
    }
    
    
    private static void generateTagCloud(MetaDataExtractor ex, String fileName)
        throws IOException {
        MetaDataVector mdv = ex.extractMetaData(title, body);
        
        FontSizeComputationStrategy strategy = 
            new LinearFontSizeComputationStrategy(numSizes,fontPrefix);
        TagCloud tagCloud = new TagCloudImpl(mdv,strategy);
        writeToFile(fileName,tagCloud);
    }
    
    private static void writeToFile(String fileName, TagCloud cloud) 
        throws IOException {
        BufferedWriter out = new BufferedWriter(
                new FileWriter(fileName));
        VisualizeTagCloudDecorator decorator = new HTMLTagCloudDecorator();
        out.write(decorator.decorateTagCloud(cloud,"",""));
        out.close();
    }
    
    public static void main(String [] args) throws Exception {
        MetaDataExtractorTest test = new MetaDataExtractorTest();
        test.testSimpleBiTermStopStemmerMetaDataExtractor();
    }
}
