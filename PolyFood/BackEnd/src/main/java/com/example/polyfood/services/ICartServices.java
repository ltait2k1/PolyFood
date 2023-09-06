package com.example.polyfood.services;

import com.example.polyfood.models.Cart;
import com.example.polyfood.models.CartItem;
import com.example.polyfood.models.responobj.CartTemp;
import com.example.polyfood.models.responobj.Respon;

import java.util.List;
import java.util.Set;

public interface ICartServices {
    public Respon<CartItem> addCartItem(int idUser,int idProduct, Integer quantity);
    public List<CartTemp> updateCartItem(int idCartItem, Integer quantity);
    public Respon<CartItem> deleteCartItem(int idCartItem);
    public Respon<Cart> deleteAll(int idCart);
    public List<CartTemp> getAll(int IdCart);
}
