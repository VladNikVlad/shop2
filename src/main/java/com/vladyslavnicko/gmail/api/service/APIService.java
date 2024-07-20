package com.vladyslavnicko.gmail.api.service;

import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.model.User;

public interface APIService {

	User saveNewUser(User user);

	ProductAPI saveProduct(ProductAPI product);

	ProductAPI findProductBy(long productId);
}
