#!/bin/bash

Dockerfile="./omtd-simple-workflows.dockerfile"
DockerImg="omtd-simple-workflows-docker"

docker build -f $Dockerfile -t $DockerImg .

