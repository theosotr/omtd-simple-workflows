#!/bin/bash

FILESIN=$1*.xmi
FILESOT=$2

NUMOFFILESWITHDIFFS=0

for f in $FILESIN
do
  echo "Comparing $f file..."
  filename=$(basename "$f")
  diff -q $f $FILESOT$filename
  #diff -q $f $f
  result=$?
  
  if [ $result -eq 1 ] 
  then
  	NUMOFFILESWITHDIFFS=$((NUMOFFILESWITHDIFFS + 1))
  fi	
done

echo "NUM OF FILES WITH DIFFS:" $NUMOFFILESWITHDIFFS
