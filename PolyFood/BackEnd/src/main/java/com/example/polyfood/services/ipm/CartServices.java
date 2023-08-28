package com.example.polyfood.services.ipm;

import com.example.polyfood.models.Cart;
import com.example.polyfood.models.CartItem;
import com.example.polyfood.models.Product;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.ICartItemRepository;
import com.example.polyfood.repository.ICartRepository;
import com.example.polyfood.repository.IProductRepository;
import com.example.polyfood.services.ICartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServices implements ICartServices {
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private IProductRepository productRepository;

    private static Respon respon = new Respon();

    @Override
    public Respon<CartItem> addCartItem(int idUser, int idProduct, Integer quantity) {
        Cart cart = new Cart();
        for (Cart temp: cartRepository.findAll()){
            if (temp.getUser().getUserId() == idUser){
                cart = temp;
                break;
            }
        }

        if (quantity == null){
            for (CartItem item: cartItemRepository.findAll()){
                if (item.getCart() == cart && item.getProduct().getProductId() == idProduct){
                    item.setQuantity(item.getQuantity() + 1);
                    respon.setData(item);
                    respon.setMassage("them thanh cong");
                    return respon;
                }
            }

            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            Product product = productRepository.getReferenceById(idProduct);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            respon.setData(cartItem);
            respon.setMassage("them thanh cong");
        }
        else {
            for (CartItem item: cartItemRepository.findAll()){
                if (item.getCart() == cart && item.getProduct().getProductId() == idProduct){
                    item.setQuantity(item.getQuantity() + quantity);
                    respon.setData(item);
                    respon.setMassage("them thanh cong");
                    return respon;
                }
            }

            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            Product product = productRepository.getReferenceById(idProduct);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            respon.setData(cartItem);
            respon.setMassage("them thanh cong");
        }
        return respon;
    }
}
