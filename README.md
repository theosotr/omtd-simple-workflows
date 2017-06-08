# OMTD-SIMPLE-WORKFLOWS #

Provides scripts and code for executing workflows within a docker container. 

## Installation ##


   
**Step 1**: Clone omtd-simple-workflows by typing
  
```
git clone <repoURL>
```


**Step 2**: CD to omtd-simple-workflows directory that has been created. For building the projects type

```
./installUNIMANProjects.sh
mvn clean install 
```

**Step 3**: Create a docker image (named `omtd-simple-workflows-docker`) that contains everything that is needed.

```
./omtd-simple-workflows-createDockerImg.sh 
```

**Step 4**: Use the following commands to  a) create a container from the image produced by the previous step b) start the container c) get a bash shell inside the container.

```
sudo docker create --name omtd-simple-workflows -t omtd-simple-workflows-docker
sudo docker start omtd-simple-workflows
sudo docker exec -i -t omtd-simple-workflows  /bin/bash
```

## Examples ##

Inside the container try the following examples. The same examples can run and in the host machine. 

**Example 1**:

```
# PDF2XMI example with DKPRo PdfReader. Reads a folder (../testInput) with PDFs and creates an output folder (../testOutput) 
# with the respective XMIs that were produced.
./Linux_runDKPro_PDF2XMI_example.sh
# Check that the produced output is correct.
./checkDiff.sh ../testOutput/ ../testOutputPDFToXMIRef/
```
**Example 2**:

```
# Chebi example from UNIMAN. Reads a folder (../testInput) with PDFs and creates an output folder (../testOutput2) 
# with the respective XMIs that were produced
./Linux_runDKPro_PDF2XMI_example.sh
```

**Example 3**:

```
# Topic Inference with DKPro (Mallet LDA). Reads a folder (../testInput) with PDFs and creates an output folder (../testOutput3) 
# with the respective XMIs that were produced
./Linux_runDKPro_PDFLDAInference_example.sh
```

NOTES: All scripts are available at `omtd-simple-workflows/scripts` directory.

 