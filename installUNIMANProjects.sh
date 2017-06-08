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

cd omtd-simple-workflows

