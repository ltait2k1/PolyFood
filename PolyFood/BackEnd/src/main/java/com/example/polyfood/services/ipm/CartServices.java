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

import java.util.Optional;

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
                    cartItemRepository.save(item);
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

    @Override
    public Respon<CartItem> updateCartItem(int idUser, int idProduct, Integer quantity) {
        Cart cart = new Cart();
        for (Cart temp: cartRepository.findAll()){
            if (temp.getUser().getUserId() == idUser){
                cart = temp;
                break;
            }
        }
        CartItem cartItem = new CartItem();
        for (CartItem item: cartItemRepository.findAll()){
            if (item.getCart() == cart && item.getProduct().getProductId() == idProduct){
                cartItem = item;
            }
        }
        if (quantity == 0){
            cartItemRepository.delete(cartItem);
            respon.setData(cartItem);
            respon.setStatus(200);
            respon.setMassage("xoa thanh cong");
        }
        else {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
            respon.setData(cartItem);
            respon.setStatus(200);
            respon.setMassage("sua thanh cong");
        }
        return respon;
    }

    @Override
    public Respon<CartItem> updateCartItem2(int idCartItem, Integer quantity) {
        Optional<CartItem> optional = cartItemRepository.findById(idCartItem);
        if (optional.isPresent()){
            CartItem cartItem = cartItemRepository.getReferenceById(idCartItem);
            if (quantity == 0){
                cartItemRepository.delete(cartItem);
                respon.setData(cartItem);
                respon.setStatus(200);
                respon.setMassage("xoa thanh cong");
            }
            else {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
                respon.setData(cartItem);
                respon.setStatus(200);
                respon.setMassage("sua thanh cong");
            }
        }
        else {
            respon.setStatus(404);
            respon.setMassage("mat hang khong ton tai");
        }
        return respon;
    }
}
