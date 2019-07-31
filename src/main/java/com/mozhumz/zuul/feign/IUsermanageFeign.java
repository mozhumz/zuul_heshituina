package com.mozhumz.zuul.feign;


import com.hyj.util.web.JsonResponse;
import com.mozhumz.zuul.model.dto.ChangePwdDto;
import com.mozhumz.zuul.model.dto.SendEmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author caijiang
 * @date 2018/3/8
 */
//@FeignClient(value = "zuul",url = "127.0.0.1:8080")
@FeignClient(value = "usermanage")
public interface IUsermanageFeign {


    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/api/user/changePwd" ,method = RequestMethod.POST)
    JsonResponse  changePwd(@RequestBody ChangePwdDto changePwdDto);

    @RequestMapping(value = "/api/sys/sendEmailCode" ,method = RequestMethod.POST)
    JsonResponse sendEmailCode(@RequestBody SendEmailDto sendEmailDto);

}

