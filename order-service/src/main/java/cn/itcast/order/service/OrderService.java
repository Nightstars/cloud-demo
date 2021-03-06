package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {

    //region initialize
    @Resource
    private OrderMapper orderMapper;

    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    //endregion

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用RestTemplate发送Http请求，查询用户
        // 2.1 url路径
        String url = "http://userservice/user/" + order.getUserId();
        // 2.2 发送Http请i去，实现远程调用
        User user = restTemplate.getForObject(url, User.class);
        // 3.封装User到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
