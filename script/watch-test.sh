#!/bin/bash

set -e
cd $(dirname $0)/..

exec sbt '~ eval "script/test.sh" !'
