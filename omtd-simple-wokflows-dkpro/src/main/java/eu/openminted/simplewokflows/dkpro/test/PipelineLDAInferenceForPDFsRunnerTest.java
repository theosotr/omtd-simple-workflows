package eu.openminted.simplewokflows.dkpro.test;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import eu.openminted.simplewokflows.dkpro.PipelineLDAInferenceForPDFs;

/**
 * @author galanisd
 *
 */
public class PipelineLDAInferenceForPDFsRunnerTest{   
	
    public static void main(String[] args) throws Exception{
    	
    	ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
    	rootLogger.setLevel(Level.toLevel("info"));
    	
    	String input = "C:/Users/galanisd/Desktop/Dimitris/EclipseWorkspaces/ILSPMars/omtd-simple-workflows/testInput/";
    	String output = "C:/Users/galanisd/Desktop/Dimitris/EclipseWorkspaces/ILSPMars/omtd-simple-workflows/testOutput/";
    	String model = "C:/Users/galanisd/Desktop/Data/CORE/_COREMalletScripts/20160408_115129model.txt";
    	
    	String [] myargs = {input, model, output};
    	PipelineLDAInferenceForPDFs.LDAInferenceForPDFs(myargs);
    }
}
