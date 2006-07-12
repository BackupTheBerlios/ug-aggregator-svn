#!/bin/sh

ant deploy || exit 1
java -jar lib/winstone-0.8.1.jar --warfile=deploy/aggregator.war

