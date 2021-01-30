package cn.ffcs.websocket.web;

import cn.ffcs.websocket.model.ChatMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p> </p>
 *
 * @ClassName WsController
 * @Author ghf
 * @Date 2021/1/28
 * @Version 1.0
 **/
@RestController
public class WsController {
    private static Logger log = LoggerFactory.getLogger(WsController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleChat(ChatMsg chatMsg) {
        chatMsg.setDate(new Date());
        log.info("发送消息:" + chatMsg);
        messagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
    @MessageMapping("/ws/test")
    public void handtest(ChatMsg chatMsg) {
        chatMsg.setDate(new Date());
        log.info("发送消息:" + chatMsg);
        messagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/test", chatMsg);
    }

    @RequestMapping("/Welcome1")
    public String say2()throws Exception{
        messagingTemplate.convertAndSend("/topic/testgroup","hello");
        return "is ok";
    }

    @MessageMapping("/testgroup")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/topic/testgroup")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public ChatMsg testgroup(ChatMsg message) throws Exception {
        log.info(message.toString());
        messagingTemplate.convertAndSend("/topic/testgroup",message);
        return message;
    }
    @MessageMapping("/group/{id}")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/topic/group/{id}")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public ChatMsg group(ChatMsg message) throws Exception {
        Thread.sleep(1000);
        log.info(message.toString());
        return message;
    }
}
