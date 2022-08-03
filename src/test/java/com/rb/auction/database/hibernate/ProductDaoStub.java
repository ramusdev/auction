package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceProductDao;
import com.rb.auction.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductDaoStub implements InterfaceProductDao {
    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public List<Product> getByName(String productName) {
        return null;
    }

    @Override
    public int addProduct(Product product) {
        return 0;
    }

    @Override
    public Optional<Product> getProductById(int productId) {
        return Optional.empty();
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public List<Product> getByUserId(int id) {
        return null;
    }
}
