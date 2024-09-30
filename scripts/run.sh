#!/usr/bin/env bash

set -o errexit
set -o errtrace
set -o nounset
set -o pipefail

pkill -9 -f flipmanager || echo "Failed to kill any apps"

echo "Running app"
mkdir -p target
nohup java -jar ../target/*.jar --server.port=9095 --spring.profiles.active=default > target/flipmanager.log 2>&1 &
echo "Starting flipmanager server"
