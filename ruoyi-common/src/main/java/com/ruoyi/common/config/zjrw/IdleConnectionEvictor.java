package com.ruoyi.common.config.zjrw;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/22 10:34 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author :CX
 * @Date :Create in 2018/8/17 10:00
 * @Effect : 定时回收没有使用的链接 交还给连接池
 */
@Component
public class IdleConnectionEvictor extends Thread {

    @Autowired
    private HttpClientConnectionManager httpClientConnectionManager;
    private volatile boolean shutdown;

    public IdleConnectionEvictor() {
        super();
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    httpClientConnectionManager.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    //关闭清理无效连接的线程
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
