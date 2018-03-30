package com.example.demo;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;
import net.dreamlu.weixin.annotation.WxApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@WxApi
public class WeixinApiController {

    @GetMapping("menu")
    @ResponseBody
    public String getMenu() {
        ApiResult apiResult = MenuApi.getMenu();
        return apiResult.getJson();
    }

}
