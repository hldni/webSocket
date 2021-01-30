package cn.ffcs.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * <p>
 *     给每一个建立连接的客户端唯一的身份
 *     可以通过 {@link StompPrincipal }中的getName进行指定通过对象
 *     一般在构建{@link StompPrincipal }时可将用户的唯一身份表示作为name,如用户名
 *
 *     在{@link WebSocketConfig}中被使用
 *
 * </p>
 *
 * @ClassName PrincipalHandshakeHandler
 * @Author ghf
 * @Date 2021/1/28
 * @Version 1.0
 **/
@Component
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {
    private Logger log = LoggerFactory.getLogger(PrincipalHandshakeHandler.class);
    private static int index = 0;
    // Custom class for storing principal
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Generate principal with UUID as name
        log.info("开始构建----StompPrincipal----");
        return new StompPrincipal(++index + "");
    }
}