#!/bin/bash

FILESIN=$1*.xmi
FILESOT=$2

NUMOFFILESWITHDIFFS=0

for f in $FILESIN
do
  echo "Comparing $f file...with the respective one from $2"
  filename=$(basename "$f")
  diff -q $f $2$filename
  #diff -q $f $f
  result=$?
  
  if [ $result -eq 1 ] 
  then
  	NUMOFFILESWITHDIFFS=$((NUMOFFILESWITHDIFFS + 1))
  fi	
done

echo "NUM OF FILES WITH DIFFS:" $NUMOFFILESWITHDIFFS
