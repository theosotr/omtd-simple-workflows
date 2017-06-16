package eu.openminted.simpleworkflows.sshner;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eu.openminted.simpleworkflows.sshner.PipelineNERInferenceForPDFs;

/**
 * @author galanisd
 *
 */
@SpringBootApplication
public class PipelineCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PipelineCommandLineRunner.class);

	// == === ==	
	public static void main(String[] args) throws Exception {

		log.info("...");
		SpringApplication app = new SpringApplication(PipelineCommandLineRunner.class);
		// app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("DONE!");
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("-->" + args[0]);
		
		// copy args
		String myArgs[] = Arrays.copyOfRange(args, 1, args.length);
		log.info("arguments ===>" + Arrays.toString(myArgs));
		
		// select workflow 
		
		if(args[0].equals(PipelineNERInferenceForPDFs.class.getName())){
			PipelineNERInferenceForPDFs.NERInferenceForPDFs(myArgs);
		}else{
			log.info("No valid workflow name/id!!");	
		}
	}
}

