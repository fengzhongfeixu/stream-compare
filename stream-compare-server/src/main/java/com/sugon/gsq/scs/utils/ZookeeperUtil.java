package com.sugon.gsq.scs.utils;

import org.apache.storm.kafka.ZkHosts;

import java.util.LinkedList;
import java.util.List;

public class ZookeeperUtil {

    public static List<String> getZkServer(ZkHosts zkHosts){
        List<String> zkServers = new LinkedList<String>();
        if(zkHosts.brokerZkStr.contains(",")){
            for (String host : zkHosts.brokerZkStr.split(",")) {
                zkServers.add(host.split(":")[0]);
            }
        }else{
            zkServers.add(zkHosts.brokerZkStr.split(":")[0]);
        }
        return zkServers;
    }

    public static Integer getZookeeperPort(String zkServer){
        Integer result = null;
        if(zkServer.contains(",")){
            result = Integer.parseInt(zkServer.split(",")[0].split(":")[1]);
        }else{
            result = Integer.parseInt(zkServer.split(":")[1]);
        }
        return result;
    }
}
