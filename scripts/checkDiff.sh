#!/bin/bash

bash removeDocumentMetadata.sh $1
bash removeDocumentMetadata.sh $2

FILESIN=$1*.xmi.NODMD
FILESOT=$2

NUMOFFILESWITHDIFFS=0

for f in $FILESIN
do
  filename=$(basename "$f")
  echo "Comparing $f <-> $FILESOT$filename"
  diff -q $f $FILESOT$filename
  #diff -q $f $f
  result=$?
  
  if [ $result -eq 1 ] 
  then
  	NUMOFFILESWITHDIFFS=$((NUMOFFILESWITHDIFFS + 1))
	#cmp -b $f $FILESOT$filename
  fi	
done

echo "NUM OF FILES WITH DIFFS:" $NUMOFFILESWITHDIFFS
