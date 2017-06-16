package eu.openminted.simpleworkflows.sshner;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @author gkirtzou
 *
 */
public class PipelineNERInferenceForPDFs {
	
	private static final URL dictionaries = PipelineNERInferenceForPDFs.class.getClassLoader().getResource("ss-io-dictionaries");
	private static final Logger logger = LogManager.getLogger(PipelineNERInferenceForPDFs.class);
			
	public static void NERInferenceForPDFs(String[] args) {
		 
		 String inputDir = args[0];
	     String outDir = args[1];
	     
	     try{
		     String tmp = Files.createTempDirectory("SSHNER").toAbsolutePath().toFile().getAbsolutePath();
		     logger.info("TMP DIR:" + tmp);   
		     
		     File outDirF = new File(outDir);
		     if(!outDirF.exists()){
		    	 outDirF.mkdirs();
		     }
		     
		     // PDF to XMI
			 logger.info("Setting parameters for PDF to XMI transformation");
		     eu.openminted.uc.socialsciences.io.pdfx.Pipeline pipelinePdf2Xmi = new eu.openminted.uc.socialsciences.io.pdfx.Pipeline();
		     pipelinePdf2Xmi.setInput(inputDir);
		     pipelinePdf2Xmi.setOutput(tmp);	   
		     pipelinePdf2Xmi.setLanguage(eu.openminted.uc.socialsciences.io.pdfx.PdfxXmlToXmiConverter.LANGUAGE_CODE_EN);
		     pipelinePdf2Xmi.setHomePath(dictionaries.toString());
		     pipelinePdf2Xmi.setOverwriteOutput(true);
		     logger.info("Running PDF to XMI transformation");
		     pipelinePdf2Xmi.run();
		     logger.info("PDF to XMI transformation finished");		       
		     	     
		     //  NER
		     logger.info("Setting parameters for NER");
		     NERPipeline pipelineNER = new NERPipeline();
		     pipelineNER.setInput(tmp + "/*.xmi");
		     pipelineNER.setOutput(outDir);
		     pipelineNER.setUseStanfordModels(true);
		     logger.info("Running NER");
		     pipelineNER.run();
		     logger.info("NER finished");
	     }catch(Exception e){
	    	 logger.info("ERROR", e);
	     }
		 
	 }

}
