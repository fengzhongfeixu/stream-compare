#!/bin/bash

PROJECT_HOME="$( dirname "$( cd "$( dirname "$0"  )" && pwd ) " )"

# This attribute must be consistent with "startup.sh" !!!
PROJECT_NAME="stream-compare-server"

pid=`ps -ef |grep "java"|grep "${PROJECT_NAME}" |grep -v "grep" |awk '{print $2}'`
if [ ${pid} ];then
    kill -9 ${pid}
    sleep 1
    if [[ $? -eq 0 ]];then
        echo "${PROJECT_NAME} stopped successfully."
        rm -f ${PROJECT_HOME}/logs/${PROJECT_NAME}.pid &>/dev/null
    else
        echo "Error! ${PROJECT_NAME} failed to stop..."
    fi
else
    echo "${PROJECT_NAME} is not running, no need to stop."
fi
exit 0
