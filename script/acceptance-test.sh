#!/bin/bash

set -e
cd $(dirname $0)/..

echo "Compiling..."
script/compile.sh

input=/tmp/test-input
expected_output=/tmp/test-expected-output
actual_output=/tmp/test-actual-output

echo ciao > $input
hexdump $input > $expected_output
script/hexdump $input > $actual_output
diff -w $expected_output $actual_output
echo "Case 5-bytes file passed"


cat src/test/resources/rails.png > $input
hexdump $input > $expected_output
script/hexdump $input > $actual_output
diff -w $expected_output $actual_output
echo "Case png file passed"
