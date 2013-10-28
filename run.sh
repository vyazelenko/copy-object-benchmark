#!/bin/bash
JAVA_EXEC=$1/bin/java

$JAVA_EXEC -showversion -server -Xms3600m -Xmx4000m -jar target/microbenchmarks.jar -gc true -foe true -f 5