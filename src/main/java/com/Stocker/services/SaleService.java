package com.Stocker.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.Stocker.dto.ProductCart;
import com.Stocker.entity.Product;
import com.Stocker.entity.Sale;
import com.Stocker.entity.User;
import com.Stocker.repository.ProductRepository;
import com.Stocker.repository.SaleRepository;

public class SaleService {
    private Map<Long, ProductCart> products;
    private SaleRepository saleRepository;
    private ProductRepository productRepository;
    private User user;

    public SaleService(User user) {
        products = new HashMap<>();
        saleRepository = new SaleRepository();
        productRepository = new ProductRepository();
        this.user = user;
    }

    public List<Sale> listSales() {
        return saleRepository.getAll(user);
    }

    public List<ProductCart> getProductsCart() {
        return products.values().stream().collect(Collectors.toList());
    }

    public void addToCart(Product product, int amount) {
        if (amount > product.getAmount()) return;
        
        ProductCart productCart = products.putIfAbsent(product.getId(), new ProductCart(product, amount));
        if (productCart != null) productCart.setAmount(productCart.getAmount() + amount);

        product.setAmount(product.getAmount() - amount);
    }

    public void updateAmount(Product product, int amount) {
        ProductCart productCart = products.get(product.getId());

        // Checa se a nova quantidade de produtos excede a quantidade total do produto
        int initialAmountProduct = productCart.getAmount() + product.getAmount();
        if (amount > initialAmountProduct) return;

        product.setAmount(initialAmountProduct - amount);
        productCart.setAmount(amount);
    }

    public void removeFromCart(Product product) {
        ProductCart productCart = products.get(product.getId());
        if (productCart == null) return;
        
        // Retorna quantidade de estoque inicial do produto
        product.setAmount(product.getAmount() + productCart.getAmount());

        products.remove(product.getId());
    }

    public void clearCart() {
        getProductsCart().forEach(p -> removeFromCart(p.getProduct()));
    }

    public void confirmSale() {
        for (ProductCart productCart : products.values()) {
            Sale sale = new Sale(null, productCart.getAmount(), productCart.getProduct().getSellingPrice(), new Date(), productCart.getProduct());

            saleRepository.save(sale);
            productRepository.update(productCart.getProduct());

            productCart.setAmount(0);
        }
        
        clearCart();
    }
}
