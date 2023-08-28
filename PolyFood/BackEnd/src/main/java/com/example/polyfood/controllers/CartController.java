package com.example.polyfood.controllers;

import com.example.polyfood.models.CartItem;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.services.ICartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/version1.0")
public class CartController {
    @Autowired
    private ICartServices cartServices;

    @PostMapping(value = "cart/addCartItem/{idUser}/{idProduct}/{quantity}")
    public Respon<CartItem> addCartItem(@PathVariable int idUser, @PathVariable int idProduct, @PathVariable Integer quantity){
        return cartServices.addCartItem(idUser,idProduct,quantity);
    }

    @PostMapping(value = "cart/addCartItem/{idUser}/{idProduct}")
    public Respon<CartItem> addCartItem(@PathVariable int idUser, @PathVariable int idProduct){
        return cartServices.addCartItem(idUser,idProduct,null);
    }

//    @PutMapping(value = "cart/updateCartItem/{idUser}/{idProduct}/{quantity}")
//    public Respon<CartItem> updateCartItem(@PathVariable int idUser, @PathVariable int idProduct, @PathVariable Integer quantity){
//        return cartServices.updateCartItem(idUser,idProduct,quantity);
//    }

    @PutMapping(value = "cart/updateCartItem/{idCartItem}/{quantity}")
    public Respon<CartItem> updateCartItem(@PathVariable int idCartItem, @PathVariable Integer quantity){
        return cartServices.updateCartItem2(idCartItem, quantity);
    }
}
