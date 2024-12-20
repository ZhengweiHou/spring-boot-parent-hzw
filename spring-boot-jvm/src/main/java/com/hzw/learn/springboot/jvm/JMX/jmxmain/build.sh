#!/bin/bash

i=$1

# clean
echo "clean"
if [ -d classdir ]
then
  rm -rf ./classdir
fi

# build
if [ $i -gt 1 ]
then
  echo "build"
  mkdir classdir
  javac -d ./classdir -verbose *.java
fi

# run
if [ $i -gt 2 ]
then
  echo "run"
  java \
  -javaagent:./jmx_prometheus_javaagent-0.12.0.jar=9999:./jmx_exporter.yml \
  -cp classdir JmxTestMain
fi
