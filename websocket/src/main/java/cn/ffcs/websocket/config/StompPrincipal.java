package cn.ffcs.websocket.config;

import java.security.Principal;

/**
 * <p>
 *     身份标识
 *     {@link PrincipalHandshakeHandler}中被使用
 * </p>
 *
 * @ClassName StompPrincipal
 * @Author ghf
 * @Date 2021/1/28
 * @Version 1.0
 **/
public class StompPrincipal implements Principal {
    private String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
