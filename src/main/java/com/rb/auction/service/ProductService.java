package com.rb.auction.service;

import com.rb.auction.database.InterfaceAuctionDao;
import com.rb.auction.database.InterfaceProductDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.Product;
import com.rb.auction.model.User;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProductService implements InterfaceProductService {
    @Autowired
    InterfaceProductDao interfaceProductDao;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    InterfaceAuctionService auctionService;

    @Override
    public List<Product> getAllProducts() {
        return this.interfaceProductDao.getProducts();
    }

    @Override
    public List<Product> getByName(String productName) {
        return this.interfaceProductDao.getByName(productName);
    }

    @Override
    public int addProduct(Product product, int auctionId) {
        User user = this.sessionObject.getUser();
        Auction auction = this.auctionService.getAuctionById(auctionId);

        product.setUser(user);
        product.setAuction(auction);

        int postId = this.interfaceProductDao.addProduct(product);

        return postId;
    }

    @Override
    public Product getProductById(int productId) {
        Optional<Product> productOptional = interfaceProductDao.getProductById(productId);

        if (productOptional.isEmpty()) {
            return null;
        }

        return productOptional.get();
    }

    @Override
    public void updateProduct(Product product) {
        interfaceProductDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByUserId(int id) {
        return this.interfaceProductDao.getByUserId(id);
    }
}
