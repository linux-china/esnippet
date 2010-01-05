#!/bin/sh
curl -s "http://www.mvnsearch.org/snippet/showPart.action?part=script&id=$1" > $2
echo "File saved: $2"