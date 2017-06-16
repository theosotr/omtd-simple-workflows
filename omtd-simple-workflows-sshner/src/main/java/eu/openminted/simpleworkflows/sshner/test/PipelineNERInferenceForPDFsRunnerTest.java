package eu.openminted.simpleworkflows.sshner.test;


import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import eu.openminted.simpleworkflows.sshner.PipelineNERInferenceForPDFs;

/**
 * @author gkirtzou
 *
 */
public class PipelineNERInferenceForPDFsRunnerTest {
	

	public static void main(String[] args) throws Exception{
	
	 	ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
	 	rootLogger.setLevel(Level.toLevel("info"));
		
	 	long startTime = System.currentTimeMillis();
	 	
	 	// Parameters
	 	//String input = "/home/gkirtzou/Dropbox/Work/Projects/OpenMinded/Data/2017.06_OMTD_Demo/Dataset2/Dataset/fulltext/";
	 	String input = "C:/Users/galanisd/Desktop/Dimitris/EclipseWorkspaces/ILSPMars/omtd-simple-workflows/testInput2/";
	 	String output = "C:/Users/galanisd/Desktop/Dimitris/EclipseWorkspaces/ILSPMars/omtd-simple-workflows/testOutput4/";
	 	
	 	// Running NER inference
	 	String [] myargs = {input, output};
    	PipelineNERInferenceForPDFs.NERInferenceForPDFs(myargs);
    	
    	long endTime   = System.currentTimeMillis();
	 	long totalTime = endTime - startTime;
	    long hours = TimeUnit.MILLISECONDS.toHours(totalTime);
	    totalTime -= TimeUnit.HOURS.toMillis(hours);
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(totalTime);
	    totalTime -= TimeUnit.MINUTES.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);

	    rootLogger.info("Total running time " + hours + "H:" + minutes + "M:" + seconds + " S");

	}

}
