package eu.openminted.simplewokflows.dkpro.test;


import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import eu.openminted.simplewokflows.dkpro.PipelineNERInferenceForPDFs;

/**
 * @author gkirtzou
 *
 */
public class PipelineNERInferenceForPDFsRunnerTest {
	

	public static void main(String[] args) throws Exception{
	
	 	ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
	 	rootLogger.setLevel(Level.toLevel("info"));
		
	 	// Parameters
	 	String input = "/home/gkirtzou/Desktop/tmp/pdf";
	 	String output = "/home/gkirtzou/Desktop/tmp/output";
	 			
	 	String [] myargs = {input, output};
    	PipelineNERInferenceForPDFs.NERInferenceForPDFs(myargs);

	}

}
