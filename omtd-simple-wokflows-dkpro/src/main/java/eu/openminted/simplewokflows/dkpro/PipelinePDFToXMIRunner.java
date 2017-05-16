package eu.openminted.simplewokflows.dkpro;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.File;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.tudarmstadt.ukp.dkpro.core.io.pdf.PdfReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;

/**
 * @author galanisd
 *
 */
@SpringBootApplication
public class PipelinePDFToXMIRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PipelinePDFToXMIRunner.class);

	// == === ==
	
	public static void main(String[] args) throws Exception {

		log.info("...");
		SpringApplication app = new SpringApplication(PipelinePDFToXMIRunner.class);
		// app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("DONE!");
	}

	@Override
	public void run(String... args) throws Exception {
		PipelinePDFToXMI.PDFToXMIPipeline(args);
	}
}
