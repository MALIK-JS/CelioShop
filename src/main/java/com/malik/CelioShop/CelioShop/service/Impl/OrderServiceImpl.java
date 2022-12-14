package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.Cart;
import com.malik.CelioShop.CelioShop.entity.order.Order;
import com.malik.CelioShop.CelioShop.entity.order.OrderDetail;
import com.malik.CelioShop.CelioShop.entity.order.OrderStatus;
import com.malik.CelioShop.CelioShop.entity.order.PaymentMethod;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.playload.CartItem;
import com.malik.CelioShop.CelioShop.playload.CheckoutDto;
import com.malik.CelioShop.CelioShop.repository.OrderDetailRepository;
import com.malik.CelioShop.CelioShop.repository.OrderRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.CartService;
import com.malik.CelioShop.CelioShop.service.OrderService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private CartService cartService;
    private ServiceHelper serviceHelper;

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    private ProductRepository productRepository;

    @Override
    @Transactional
    public void placeOrder(CheckoutDto checkoutDto) {
        User user = serviceHelper.getAuthenticatedUser();

        CartItem cart = cartService.getCartOfAuthenticatedUser();

        List<Cart> cartItems = cart.getItems();

        Order newOrder = new Order();
        newOrder.setAddress(checkoutDto.getAddress());
        newOrder.setPhoneNumber(checkoutDto.getPhoneNumber());
        newOrder.setOrderStatus(OrderStatus.PROCESSING);
        newOrder.setPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);
        newOrder.setSubtotal(cart.getTotalPrice());
        orderRepository.save(newOrder);

        for ( Cart item : cartItems){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setUnitPrice(item.getPrice());
            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(item.getProduct());
            orderDetailRepository.save(orderDetail);
            int remainingQuantity = item.getProduct().getQuantity() - item.getQuantity();
            productRepository.updateProductQuantity(item.getProduct().getId(),remainingQuantity);
        }

    }
}
