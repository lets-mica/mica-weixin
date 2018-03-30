package com.example.demo;

import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import net.dreamlu.weixin.annotation.WxMsgController;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.beans.factory.annotation.Autowired;

@WxMsgController("/weixin/wx")
public class WeixinController extends MsgControllerAdapter {

    @Autowired
    private DreamWeixinProperties weixinProperties;

    @Override
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        outMsg.setContent("关注消息~");
        render(outMsg);
    }

    @Override
    protected void processInTextMsg(InTextMsg inTextMsg) {
        System.out.println(weixinProperties.getWxaConfig().getAppId());
        OutTextMsg outMsg = new OutTextMsg(inTextMsg);
        outMsg.setContent("文本消息~");
        render(outMsg);
    }

    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
        outMsg.setContent("菜单消息~");
        render(outMsg);
    }
}
