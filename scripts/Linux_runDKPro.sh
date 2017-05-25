#!/bin/bash

java -Xmx4096m -jar -Dfile.encoding=UTF-8  $(pwd)"/../omtd-simple-wokflows-dkpro/target/omtd-simple-wokflows-dkpro-0.0.1-SNAPSHOT-exec.jar" $@
