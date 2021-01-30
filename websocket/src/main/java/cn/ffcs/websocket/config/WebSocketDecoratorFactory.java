package cn.ffcs.websocket.config;

import cn.ffcs.websocket.service.SocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;

/**
 * <p>
 *     连接和退出连接时使用
 * </p>
 *
 * @ClassName WebSocketDecoratorFactory
 * @Author ghf
 * @Date 2021/1/28
 * @Version 1.0
 **/
@Component
public class WebSocketDecoratorFactory implements WebSocketHandlerDecoratorFactory {
    private static Logger log = LoggerFactory.getLogger(WebSocketDecoratorFactory.class);

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                log.info("有人连接啦  sessionId = {}" , session.getId());
                Principal principal = session.getPrincipal();
                if (principal != null) {
                    log.info("key = {} 存入" , principal.getName());
                    SocketManager.add(principal.getName(), session);
                    // 身份校验成功，缓存socket连接
                }
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                log.info("有人退出连接啦  sessionId = {}" , session.getId());
                Principal principal = session.getPrincipal();
                if (principal != null) {
//                     身份校验成功，移除socket连接
                    SocketManager.remove(principal.getName());
                }
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
