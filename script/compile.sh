#!/bin/bash

set -e
cd $(dirname $0)/..

output=target

# Why not use sbt for compiling?  Because it fails on my machine

mkdir -p $output 2> /dev/null
scalac -d $output -cp 'lib/*' src/*/scala/*.scala


