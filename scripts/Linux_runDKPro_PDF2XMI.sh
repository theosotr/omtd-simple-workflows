#!/bin/bash

#java -jar $(pwd)"/../omtd-simple-wokflows-dkpro/target/omtd-simple-wokflows-dkpro-0.0.1-SNAPSHOT-exec.jar" $1 $2
java -jar -Dfile.encoding=UTF-8  $(pwd)"/../omtd-simple-wokflows-dkpro/target/omtd-simple-wokflows-dkpro-0.0.1-SNAPSHOT-exec.jar" $1 $2
