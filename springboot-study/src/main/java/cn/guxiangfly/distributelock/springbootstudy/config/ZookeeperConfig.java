package cn.guxiangfly.distributelock.springbootstudy.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * 思路如下： 首先创建一个 CountDownLatch  和 zkclient
 *
 * 如果 getLock 思路：如果说 锁路径已经存在，代表已经被锁了  获取锁失败就尝试等待， 通过 countdownlatch.await() 阻塞 等待被唤醒 获取锁
 *                  zookeeper会监测文件下路径的变化，如果锁路径被删除了 就代表锁可以重新获取了， 就通过countdownlatch.countDown来唤醒他
 *
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/3 14:48
 */
@Configuration
@Slf4j
public class ZookeeperConfig {
//
//    private CuratorFramework client = null;
//
//    public static final String ZK_LOCK = "pk-zk-locks";
//
//    public static final String DISTRIBUTED_LOCK = "pk-distributed-lock";
//
//    public static CountDownLatch countDownLatch = new CountDownLatch(1);
//
//    ZookeeperConfig(){
//        client = CuratorFrameworkFactory.builder()
//                .connectString("192.168.25.101:2181")
//                .sessionTimeoutMs(10000)
//                .retryPolicy(
//                        new ExponentialBackoffRetry(1000, 3))
//                .namespace("zk-namespace").build();
//        client.start();
//    }
//
//    @Bean
//    public CuratorFramework getClient(){
//       client = client.usingNamespace("zk-namespace");
//        try {
//            if (client.checkExists().forPath("/"+ZK_LOCK)==null){
//                client.create()
//                        .creatingParentsIfNeeded()
//                        .withMode(CreateMode.PERSISTENT)
//                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE);
//            }
//
//            addWatch("/"+ZK_LOCK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return client;
//    }
//
//    public void addWatch(String path) throws Exception {
//        PathChildrenCache cache = new PathChildrenCache(client, path, true);
//        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
//        cache.getListenable().addListener(new PathChildrenCacheListener() {
//            @Override
//            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
//                if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
//                    String path1 = event.getData().getPath();
//                    if (path.contains(DISTRIBUTED_LOCK)){
//                        countDownLatch.countDown();
//                    }
//                }
//            }
//        });
//    }
//
//    public void getLock(){
//        try {
//            client.create().creatingParentsIfNeeded()
//                    .withMode(CreateMode.EPHEMERAL)
//                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                    .forPath("/"+ZK_LOCK+"/"+DISTRIBUTED_LOCK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("等待获取锁");
//            if (countDownLatch.getCount()<=0){
//                countDownLatch = new CountDownLatch(1);
//            }
//
//            try {
//                countDownLatch.await();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
//        log.info("分布式锁获取成功");
//    }
//
//
//    public boolean releaseLock(){
//        try {
//            if (client.checkExists().forPath("/"+ZK_LOCK+"/"+DISTRIBUTED_LOCK) !=null){
//                client.delete().forPath("/"+ZK_LOCK+"/"+DISTRIBUTED_LOCK);
//            }
//            log.info("分布式锁释放成功");
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
