package com.Stocker.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.Stocker.dto.Notification;
import com.Stocker.entity.Product;
import com.Stocker.entity.Sale;
import com.Stocker.entity.User;
import com.Stocker.repository.SaleRepository;

public class NotificationService {
    private static final int numDays = 7;
    private SaleRepository saleRepository;
    private User user;

    public NotificationService(User user) {
        saleRepository = new SaleRepository();
        this.user = user;
    }

    public List<Notification> execute() {       
        // Vendas dos ultimos `numDays` dias
        List<Sale> sales = saleRepository.getByDate(
            user, 
            new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(numDays)), 
            new Date()
        ); 
    
        Map<Long, Notification> notifications = new HashMap<>();

        // Calcula as vendas de cada produto
        sales.forEach(sale -> {
            Notification ntf = notifications.computeIfAbsent(
                sale.getProduct().getId(), 
                k -> new Notification(sale.getProduct(), numDays)
            );
            ntf.addSales(sale.getAmount());
        });

        List<Notification> filteredNotifications = notifications.values()
            .stream()
            .filter(n -> n.getNumDaysRemainingStock() <= n.getMinDeliveryTime())
            .collect(Collectors.toList());

        return filteredNotifications;
    }
}
