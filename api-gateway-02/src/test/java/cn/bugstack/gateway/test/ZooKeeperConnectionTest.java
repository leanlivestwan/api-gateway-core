package cn.bugstack.gateway.test;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.WatchedEvent;

public class ZooKeeperConnectionTest {
    public static void main(String[] args) {
        String connectString = "120.78.190.233:2181";
        int sessionTimeout = 5000; // 会话超时时间，以毫秒为单位

        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                public void process(WatchedEvent event) {
                    // 处理ZooKeeper事件
                    System.out.println("Received event: " + event.getType());
                }
            });

            // 等待连接建立
            while (zooKeeper.getState() != ZooKeeper.States.CONNECTED) {
                Thread.sleep(100);
            }

            // 连接成功
            System.out.println("Connected to ZooKeeper!");

            // 关闭连接
            zooKeeper.close();
        } catch (Exception e) {
            // 连接失败
            System.out.println("Failed to connect to ZooKeeper: " + e.getMessage());
        }
    }
}
