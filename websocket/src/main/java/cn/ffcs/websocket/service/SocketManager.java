package cn.ffcs.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *    添加、移除连接
 * </p>
 *
 * @ClassName SocketManager
 * @Author ghf
 * @Date 2021/1/28
 * @Version 1.0
 **/
public class SocketManager {
    private static Logger log = LoggerFactory.getLogger(SocketManager.class);
    private static ConcurrentHashMap<String, WebSocketSession> manager = new ConcurrentHashMap<String, WebSocketSession>();

    public static void add(String key, WebSocketSession webSocketSession) {
        log.info("新添加webSocket连接 {} ", key);
        manager.put(key, webSocketSession);
    }

    public static void remove(String key) {
        log.info("移除webSocket连接 {} ", key);
        manager.remove(key);
    }

    public static WebSocketSession get(String key) {
        log.info("获取webSocket连接 {}", key);
        return manager.get(key);
    }
}
