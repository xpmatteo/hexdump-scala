#!/bin/bash

set -e
cd $(dirname $0)/..

input=/tmp/test-input
expected_output=/tmp/test-expected-output
actual_output=/tmp/test-actual-output

echo ciao > $input
hexdump $input > $expected_output
script/hexdump $input > $actual_output
diff $expected_output $actual_output
