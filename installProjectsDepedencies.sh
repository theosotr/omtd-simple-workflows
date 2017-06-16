#!/bin/bash

cd ..
git clone https://github.com/argo-nactem/argo-type-systems.git
cd argo-type-systems
mvn clean install

cd ..
git clone https://github.com/argo-nactem/omtd-remote-execution
cd omtd-remote-execution
mvn clean install

cd ..
#git clone https://github.com/gkirtzou/uc-tdm-socialsciences.git
git clone https://github.com/openminted/uc-tdm-socialsciences.git
cd uc-tdm-socialsciences
#git checkout omtd-demo
git checkout 0fc56c50090c2e8821dc692dbe122d3475415146
mvn clean install -Dmaven.test.skip=true

cd ..
cd omtd-simple-workflows

