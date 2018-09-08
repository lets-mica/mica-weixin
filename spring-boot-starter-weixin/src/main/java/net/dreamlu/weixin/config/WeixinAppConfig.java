package net.dreamlu.weixin.config;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import lombok.AllArgsConstructor;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AutoConfigureAfter(DreamWeixinAutoConfiguration.class)
@AllArgsConstructor
public class WeixinAppConfig implements InitializingBean {
    private final DreamWeixinProperties weixinProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        boolean isdev = weixinProperties.isDevMode();
        ApiConfigKit.setDevMode(isdev);
        List<DreamWeixinProperties.ApiConfig> list = weixinProperties.getWxConfigs();
        for (DreamWeixinProperties.ApiConfig apiConfig : list) {
            ApiConfig config = new ApiConfig();
            config.setAppId(apiConfig.getAppId());
            config.setAppSecret(apiConfig.getAppSecret());
            config.setToken(apiConfig.getToken());
            config.setEncodingAesKey(apiConfig.getEncodingAesKey());
            config.setEncryptMessage(apiConfig.isMessageEncrypt());
            ApiConfigKit.putApiConfig(config);
        }
        DreamWeixinProperties.WxaConfig wxaConfig = weixinProperties.getWxaConfig();
        WxaConfig config = new WxaConfig();
        config.setAppId(wxaConfig.getAppId());
        config.setAppSecret(wxaConfig.getAppSecret());
        config.setToken(wxaConfig.getToken());
        config.setEncodingAesKey(wxaConfig.getEncodingAesKey());
        config.setMessageEncrypt(wxaConfig.isMessageEncrypt());
        WxaConfigKit.setDevMode(isdev);
        WxaConfigKit.setWxaConfig(config);
        if (WxaMsgParser.JSON == weixinProperties.getWxaMsgParser()) {
            WxaConfigKit.useJsonMsgParser();
        }
    }

}
