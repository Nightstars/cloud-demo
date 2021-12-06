package com.smartsoft.feign.clients;

import com.smartsoft.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice")
public interface UserClient {

    //region findById
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
    //endregion
}
