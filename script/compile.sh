#!/bin/bash

set -e
cd $(dirname $0)/..

output=target

mkdir -p $output 2> /dev/null
scalac -d $output -cp 'lib/*' src/*/scala/*.scala


