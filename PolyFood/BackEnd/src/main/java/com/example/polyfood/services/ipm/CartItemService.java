package com.example.polyfood.services.ipm;

import com.example.polyfood.repository.ICartItemRepository;
import com.example.polyfood.services.ICartItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService implements ICartItemServices {
    @Autowired
    private ICartItemRepository cartItemRepository;
}
