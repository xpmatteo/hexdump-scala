#!/bin/bash

set -e

output=target

mkdir -p $output 2> /dev/null
scalac -d $output -cp 'lib/*' src/test/scala/*
scala -cp 'lib/*:$output' org.scalatest.tools.Runner -o -R $output 


