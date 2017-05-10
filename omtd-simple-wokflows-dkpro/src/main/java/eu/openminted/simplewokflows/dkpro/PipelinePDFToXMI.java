package eu.openminted.simplewokflows.dkpro;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.File;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.io.pdf.PdfReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;


/**
 * @author galanisd
 *
 */
public class PipelinePDFToXMI {

  public static void main(String[] args) throws Exception {
	  
	  if(args.length == 2 && !args[0].isEmpty() && !args[1].isEmpty()){
		  String inputFolder = args[0];
		  String outputFolder = args[1];
		  
		  File inputFolderFile = new File(inputFolder);
		  File outputFolderFile = new File(outputFolder);
		  
		  if(!outputFolderFile.exists()){
			  outputFolderFile.mkdirs();
		  }
		  
		  if(inputFolderFile.isDirectory() && outputFolderFile.isDirectory()){
		  	  			  
			  CollectionReader reader = createReader(PdfReader.class, 
					  PdfReader.PARAM_SOURCE_LOCATION, inputFolder, 
					  PdfReader.PARAM_PATTERNS, "[+]**/*.pdf");

			  AnalysisEngine engine = createEngine(XmiWriter.class,
					  XmiWriter.PARAM_TARGET_LOCATION, outputFolder, 
					  XmiWriter.PARAM_OVERWRITE, Boolean.TRUE);
			  
			  SimplePipeline.runPipeline(reader, engine);
			  System.out.println("DONE!");
		  }else{
			  System.out.println("Check Input or output folders");  
		  }
	  }else{
		  System.out.println("Check arguments");
	  }
  }
}
