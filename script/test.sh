#!/bin/bash

set -e
cd $(dirname $0)/..

output=target

echo "Compiling..."
script/compile.sh
scala -cp 'lib/*:$output' org.scalatest.tools.Runner -o -R $output 


