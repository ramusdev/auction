package com.rb.auction.service;

import com.rb.auction.database.InterfaceProductDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.Product;
import com.rb.auction.model.Tag;
import com.rb.auction.model.User;
import com.rb.auction.model.view.AuctionView;
import com.rb.auction.model.view.TagView;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductService implements InterfaceProductService {
    @Autowired
    InterfaceProductDao interfaceProductDao;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    InterfaceAuctionService auctionService;
    @Autowired
    InterfaceTagService tagService;

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
        Auction auction = this.auctionService.getById(auctionId);

        product.setUser(user);
        product.setAuction(auction);

        int postId = this.interfaceProductDao.addProduct(product);

        return postId;
    }

    @Override
    public Product getById(int productId) {
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

    @Override
    public void addProductAuctionTags(Product product, AuctionView auctionView, TagView tagView) {
        String[] tagSlugs = tagView.getTagField();

        List<Tag> tags = Arrays.stream(tagSlugs).map(e -> {
            Optional<Tag> tag = this.tagService.getBySlug(e);
            return tag.get();
        }).collect(Collectors.toList());

        User user = this.sessionObject.getUser();
        product.setUser(user);

        Auction auction = this.auctionService.addAuction(auctionView);

        // this.interfaceProductDao.addProduct(product);
        product.getTags().addAll(tags);

        product.setAuction(auction);
        // this.interfaceProductDao.updateProduct(product);
        this.interfaceProductDao.addProduct(product);


    }
}
