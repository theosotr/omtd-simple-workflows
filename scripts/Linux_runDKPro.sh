#!/bin/bash

java -Xmx4096m -jar -Dfile.encoding=UTF-8  $(pwd)"/../omtd-simple-workflows-dkpro/target/omtd-simple-workflows-dkpro-0.0.1-SNAPSHOT-exec.jar" $@
