#!/bin/bash

PROJECT_HOME="$( dirname "$( cd "$( dirname "$0"  )" && pwd ) " )"

${PROJECT_HOME}/bin/stop.sh

${PROJECT_HOME}/bin/startup.sh
