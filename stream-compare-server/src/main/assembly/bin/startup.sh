#!/bin/bash

PROJECT_HOME="$( dirname "$( cd "$( dirname "$0"  )" && pwd ) " )"

# This attribute must be consistent with "stop.sh" !!!
PROJECT_NAME="stream-compare-server"

# This is the main class of methods.
MAIN_CLASS="com.sugon.gsq.scs.Application"

# This is java_home of Linux system.
export JAVA_HOME=/apps/srv/jdk/bin

JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Xmx3096m"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+UseG1GC -verbose:gc"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+PrintGCDetails"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+PrintGCTimeStamps"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+PrintGCDateStamps"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Xloggc:${PROJECT_HOME}/logs/${PROJECT_NAME}-gc.log"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:HeapDumpPath=${PROJECT_HOME}/logs/heapdump.hprof"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+UseGCLogFileRotation"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:GCLogFileSize=128M -XX:NumberOfGCLogFiles=4"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote=false"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote.port=30005"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"

pid=`ps -ef |grep "java"|grep "${PROJECT_NAME}" |grep -v "grep" |awk '{print $2}'`
if [ -f ${PROJECT_HOME}/logs/${PROJECT_NAME}.pid ];then
    echo "Error! ${PROJECT_NAME} is running and pid is ${pid}, please stop it first."
    exit 1
else
    #set classpath
    for j in ${PROJECT_HOME}/lib/*.jar;do
        CLASSPATH=${j}:"${CLASSPATH}"
    done
    CLASSPATH="${PROJECT_HOME}/conf:${CLASSPATH}"

    #nohup java -jar
    nohup ${JAVA_HOME}/java ${JAVA_CMD_OPTS} -classpath .:${CLASSPATH} ${MAIN_CLASS} -Dglobal.config.path=${PROJECT_HOME}/conf/ --spring.config.location=${PROJECT_HOME}/conf/application.yml &>>${PROJECT_HOME}/logs/${PROJECT_NAME}.log &

    sleep 2
    pid=`ps -ef |grep "java"|grep "${PROJECT_NAME}" |grep -v "grep" |awk '{print $2}'`
    if [ ${pid} ];then
        echo "${PROJECT_NAME} started successfully."
        echo ${pid} > ${PROJECT_HOME}/logs/${PROJECT_NAME}.pid
    else
        echo "Error! ${PROJECT_NAME} failed to start... please check the logs."
        exit 1
    fi
fi
exit 0
