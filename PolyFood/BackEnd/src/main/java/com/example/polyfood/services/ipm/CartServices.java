package com.example.polyfood.services.ipm;

import com.example.polyfood.models.Cart;
import com.example.polyfood.models.CartItem;
import com.example.polyfood.models.Product;
import com.example.polyfood.models.responobj.CartTemp;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.ICartItemRepository;
import com.example.polyfood.repository.ICartRepository;
import com.example.polyfood.repository.IProductRepository;
import com.example.polyfood.services.ICartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServices implements ICartServices {
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private IProductRepository productRepository;

    private static Respon respon = new Respon();

    private static List<CartTemp> cartTemps = new ArrayList<>();

    @Override
    public Respon<CartItem> addCartItem(int idCart, int idProduct, Integer quantity) {
        Cart cart = cartRepository.getReferenceById(idCart);
        if (quantity == null){
            for (CartItem item: cart.getCartItems()){
                if (item.getProduct().getProductId() == idProduct){
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
            for (CartItem item: cart.getCartItems()){
                if (item.getProduct().getProductId() == idProduct){
                    item.setQuantity(item.getQuantity() + quantity);
                    cartItemRepository.save(item);
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
    public List<CartTemp> updateCartItem(int idCartItem, Integer quantity) {
        Optional<CartItem> optional= cartItemRepository.findById(idCartItem);
        if (optional.isPresent()){
            cartTemps.clear();
            CartItem cartItem = cartItemRepository.getReferenceById(idCartItem);
            if (quantity == 0){
                cartItemRepository.delete(cartItem);
                getAll(cartItem.getCart().getCartId());
            }
            else {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
                getAll(cartItem.getCart().getCartId());
            }
        }
        return cartTemps;
    }

    @Override
    public List<CartTemp> deleteCartItem(int idCartItem) {
        Optional<CartItem> optional = cartItemRepository.findById(idCartItem);
        if (optional.isPresent()){
            System.out.println(1);
            CartItem cartItem = cartItemRepository.getReferenceById(idCartItem);
            cartItemRepository.delete(cartItem);
            cartTemps.clear();
            getAll(cartItem.getCart().getCartId());
        }
        return cartTemps;
    }

    @Override
    public List<CartTemp> deleteAll(int idCart) {
        Optional<Cart> optional = cartRepository.findById(idCart);
        if (optional.isPresent()){
            for (CartItem cartItem: cartItemRepository.findAll()){
                if (cartItem.getCart().getCartId() == idCart){
                    cartItemRepository.delete(cartItem);
                }
            }
            cartTemps.clear();
        }
        return cartTemps;
    }

    @Override
    public List<CartTemp> getAll(int idCart) {
        cartTemps.clear();
        for (CartItem item: cartItemRepository.findAll()){
            if (item.getCart().getCartId() == idCart){
                CartTemp cartTemp = new CartTemp();
                cartTemp.setCartItemId(item.getCartItemId());
                cartTemp.setQuantity(item.getQuantity());
                cartTemp.setProductId(item.getProduct().getProductId());
                cartTemp.setNameProduct(item.getProduct().getNameProduct());
                cartTemp.setAvatarImageProduct(item.getProduct().getAvatarImageProduct());
                cartTemp.setPrice(item.getProduct().getPrice());
                double OriginalPrice = cartTemp.getPrice() * ((100.0 - item.getProduct().getDiscount())/100);
                cartTemp.setOriginalPrice(OriginalPrice);
                cartTemp.setSubtotal(cartTemp.getOriginalPrice() * cartTemp.getQuantity());
                cartTemps.add(cartTemp);
            }
        }

        return cartTemps;
    }

    @Override
    public List<CartItem> view(int idCart) {
        List<CartItem> cartItemList = new ArrayList<>();
        for (CartItem item: cartItemRepository.findAll()){
            if (item.getCart().getCartId() == idCart){
//                CartItem cartItem = new CartItem();
//                cartItem.setCartItemId(item.getCartItemId());
//                cartItem.setQuantity(item.getQuantity());
//                cartItem.setProduct(item.getProduct());
//                cartItem.setCart(item.getCart());
                cartItemList.add(item);
            }
        }
        return cartItemList;
    }
}
