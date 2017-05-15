#!/bin/bash


# Remove NODMD files
rm -rf DIR*.NODMD

# List all xmi files.
DIR=$1*.xmi

# For each xmi.
for f in $DIR
do
  echo "Removing type:DocumentMetaData from $f file..."
  #filename=$(basename "$f")
  # delete the required tag and save ouput to a new file.
  xmlstarlet ed --delete "/xmi:XMI/type:DocumentMetaData" $f > $f.NODMD
done

