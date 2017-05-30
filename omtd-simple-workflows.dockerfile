FROM ubuntu:14.04

RUN locale-gen en_US.UTF-8
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

#ENV LANG C.UTF-8

# Install java.
# -- -- --- - -- -- -- --- - -- 
RUN apt-get update && apt-get -y upgrade && apt-get -y install software-properties-common && add-apt-repository ppa:webupd8team/java -y && apt-get update
RUN (echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections) && apt-get install -y oracle-java8-installer oracle-java8-set-default
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH $JAVA_HOME/bin:$PATH
ENV JAR_FILE=/opt/omtd-simple-workflows/omtd-simple-workflows-dkpro-0.0.1-SNAPSHOT-exec.jar

# Install xmlstarlet
RUN apt-get -y install xmlstarlet

# Install omtd-simple-workflows. 
# -- -- --- - -- -- -- --- - -- 
# Create target dir.
RUN mkdir /opt/omtd-simple-workflows/
# Copy everything to target dir.
COPY omtd-simple-workflows-dkpro/target/omtd-simple-workflows-dkpro-0.0.1-SNAPSHOT-exec.jar /opt/omtd-simple-workflows/
COPY scripts/ /usr/bin/

# Add some data for testing reasons.
COPY testInput /root/input_dataset/
COPY testOutputPDFToXMIRef /root/test_dataset

# Set working dir. 
WORKDIR /root

# -- -- --- - -- -- -- --- - -- 


