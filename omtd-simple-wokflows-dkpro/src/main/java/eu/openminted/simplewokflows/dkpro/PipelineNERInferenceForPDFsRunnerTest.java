package eu.openminted.simplewokflows.dkpro;


import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;



//import eu.openminted.uc.socialsciences.io.pdfx.Pipeline;
import eu.openminted.uc.socialsciences.ner.main.Pipeline;

public class PipelineNERInferenceForPDFsRunnerTest {
	

	public static void main(String[] args) throws Exception{
	
	 	ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
	 	rootLogger.setLevel(Level.toLevel("info"));
		
	 	// Parameters
	 	String pdfFolder = "/home/gkirtzou/Desktop/tmp/pdf";
	 	String xmiFolder = "/home/gkirtzou/Desktop/tmp/xmi";
	 	String dictionaryFolder = "/home/gkirtzou/Desktop/tmp/ss-io-dictionaries";
	 	String nerFolder = "/home/gkirtzou/Desktop/tmp/annotated-xmi";
		
    	// PDF to XMI
	 	rootLogger.info("Setting parameters for PDF to XMI transformation");
        eu.openminted.uc.socialsciences.io.pdfx.Pipeline pipelinePdf2Xmi = new eu.openminted.uc.socialsciences.io.pdfx.Pipeline();
        pipelinePdf2Xmi.setInput(pdfFolder);
        pipelinePdf2Xmi.setOutput(xmiFolder);
        pipelinePdf2Xmi.setLanguage(eu.openminted.uc.socialsciences.io.pdfx.PdfxXmlToXmiConverter.LANGUAGE_CODE_EN);
        pipelinePdf2Xmi.setHomePath(dictionaryFolder);
        pipelinePdf2Xmi.setOverwriteOutput(true);
        rootLogger.info("Running PDF to XMI transformation");
        pipelinePdf2Xmi.run();
        rootLogger.info("PDF to XMI transformation finished");
        
        //  NER
        rootLogger.info("Setting parameters for NER");
    	Pipeline pipelineNER = new Pipeline();
    	pipelineNER.setInput(xmiFolder + "/*.xmi");
    	pipelineNER.setOutput(nerFolder);
    	pipelineNER.setUseStanfordModels(true);
    	rootLogger.info("Running NER");
    	pipelineNER.run();
    	rootLogger.info("NER finished");
		

	}

}
