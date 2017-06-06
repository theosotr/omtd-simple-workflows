package eu.openminted.simpleworkflows.uniman;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.File;
import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import de.tudarmstadt.ukp.dkpro.core.io.pdf.PdfReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import eu.openminted.remoteexecution.client.RemoteComponent;

public class ChebiPipeline {
	@Option(name = "-i", usage = "Folder containing input files", metaVar = "INPUT_FOLDER", required = true)
	private File inputFolder;

	@Option(name = "-o", usage = "Folder to hold output files", metaVar = "OUTPUT_FOLDER", required = true)
	private File outputFolder;

	@Option(name = "-url", usage = "URL of Chebi web service", metaVar = "URL", required = false)
	private String chebiUrl = "http://nactem.ac.uk/api/openminted/chebi";

	ChebiPipeline(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);
		try {
			parser.parseArgument(args);

			if (!inputFolder.exists()) {
				throw new CmdLineException(parser,
						new IOException("Input folder '" + inputFolder + "' does not exist"));
			}

			if (!inputFolder.isDirectory()) {
				throw new CmdLineException(parser,
						new IOException("Input folder '" + inputFolder + "' is a file and not a folder"));
			}

			if (!outputFolder.exists()) {
				outputFolder.mkdirs();
			} else if (!outputFolder.isDirectory()) {
				throw new CmdLineException(parser,
						new IOException("Output folder '" + inputFolder + "' is a file and not a folder"));
			}

		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
			System.err.println();
			System.exit(1);
		}
	}

	void run() throws UIMAException, IOException {
		CollectionReader reader = createReader(PdfReader.class, PdfReader.PARAM_SOURCE_LOCATION, inputFolder,
				PdfReader.PARAM_PATTERNS, "[+]**/*.pdf");

		AnalysisEngine chebi = createEngine(RemoteComponent.class, RemoteComponent.URL_PARAM, chebiUrl);

		AnalysisEngine writer = createEngine(XmiWriter.class, XmiWriter.PARAM_TARGET_LOCATION, outputFolder,
				XmiWriter.PARAM_OVERWRITE, Boolean.TRUE);

		SimplePipeline.runPipeline(reader, chebi, writer);

	}

	public static void main(String[] args) throws UIMAException, IOException {
		new ChebiPipeline(args).run();
	}

	public static class TestEngine extends JCasAnnotator_ImplBase {
		@Override
		public void process(JCas jcas) throws AnalysisEngineProcessException {
			// System.out.println(jcas.getDocumentText());
			for (Annotation annotation : jcas.getAnnotationIndex()) {
				System.out.println(annotation.getType().toString() + ": " + annotation.getCoveredText());
			}
		}
	}
}
