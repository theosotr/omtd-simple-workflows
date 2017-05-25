package eu.openminted.simplewokflows.dkpro;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.io.pdf.PdfReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.mallet.topicmodel.MalletTopicModelInferencer;
import de.tudarmstadt.ukp.dkpro.core.mallet.type.TopicDistribution;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;

import static org.apache.uima.fit.util.JCasUtil.select;

/**
 * @author galanisd
 *
 */
public class PipelineLDAInferenceForPDFs{
    
    private static final String LANGUAGE = "en";
    private static final URL STOPWORD_FILE = PipelineLDAInferenceForPDFs.class.getClassLoader().getResource("stopwords_en.txt");
    
    public static void LDAInferenceForPDFs(String[] args) throws IOException, UIMAException{
        String inputDir = args[0];
        String modelFile = args[1];
        String outDir = args[2];
        
        File outDirF = new File(outDir);
        if(!outDirF.exists()){
        	outDirF.mkdirs();
        }
        
        //CollectionReaderDescription reader = createReaderDescription(TextReader.class,
        //       TextReader.PARAM_SOURCE_LOCATION, inputDir,
        //        TextReader.PARAM_LANGUAGE, LANGUAGE);
        
        CollectionReaderDescription reader = createReaderDescription(PdfReader.class, PdfReader.PARAM_SOURCE_LOCATION, inputDir,
				PdfReader.PARAM_PATTERNS, "[+]**/*.pdf", 
				PdfReader.PARAM_LANGUAGE, LANGUAGE);
				//);
		
        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription stopwordRemover = createEngineDescription(StopWordRemover.class,
                StopWordRemover.PARAM_MODEL_LOCATION, STOPWORD_FILE);
        AnalysisEngineDescription lda = createEngineDescription(MalletTopicModelInferencer.class,
                MalletTopicModelInferencer.PARAM_MODEL_LOCATION, modelFile);

        AnalysisEngineDescription xmiWriter = createEngineDescription(XmiWriter.class, XmiWriter.PARAM_TARGET_LOCATION, outDir,
				XmiWriter.PARAM_OVERWRITE, Boolean.TRUE);
        
        PrintStream p = null;
        for (JCas jcas : SimplePipeline.iteratePipeline(reader, segmenter, stopwordRemover, lda, xmiWriter)) {
        	
        	DocumentMetaData meta = DocumentMetaData.get(jcas);
        	
            select(jcas, TopicDistribution.class).forEach(System.out::println);        	
            //select(jcas, TopicDistribution.class).forEach(System.out::println);
            
            AnnotationIndex<TopicDistribution> index = (AnnotationIndex<TopicDistribution>)jcas.getAnnotationIndex(TopicDistribution.class);            
            
        	File f = new File(outDir + meta.getDocumentId());        	
        	p = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)));
        	p.println(index.iterator().next().getTopicProportions().toString());
            p.flush();
            p.close();
            
        }
    }
}
