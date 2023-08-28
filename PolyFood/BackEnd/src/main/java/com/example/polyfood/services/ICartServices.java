package com.example.polyfood.services;

import com.example.polyfood.models.CartItem;
import com.example.polyfood.models.responobj.Respon;

public interface ICartServices {
    public Respon<CartItem> addCartItem(int idUser,int idProduct, Integer quantity);
    public Respon<CartItem> updateCartItem(int idUser,int idProduct, Integer quantity);
    public Respon<CartItem> updateCartItem2(int idCartItem, Integer quantity);
    public Respon<CartItem> delete(int idCartItem);
}
