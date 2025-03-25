package com.code.mckvie;
import com.code.mckvie.model.*;
import com.code.mckvie.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            //Checking if category already exists
            Query<Category> categoryQuery = session.createQuery("FROM Category WHERE name = :name", Category.class);
            categoryQuery.setParameter("name", "Makeup");
            Category makeup = categoryQuery.uniqueResult();

            if (makeup == null) {
                makeup = new Category("Makeup", "makeup items");
                session.save(makeup);
            }

            // Checking if product already exists
            Query<Product> productQuery = session.createQuery("FROM Product WHERE name = :name", Product.class);
            productQuery.setParameter("name", "Blush");
            Product blush = productQuery.uniqueResult();

            if (blush == null) {
                blush = new Product("Blush", 795.99, 50, makeup);
                session.save(blush);
            }

            // Checking if user already exists
            Query<Users> userQuery = session.createQuery("FROM Users WHERE username = :username", Users.class);
            userQuery.setParameter("username", "ritisha");
            Users user = userQuery.uniqueResult();

            if (user == null) {
                user = new Users("ritisha", "password123", "ritisha@example.com", Users.Role.CUSTOMER);
                session.save(user);
            }

            //  Creating Order with dynamic total amount
            int orderQuantity = 1;
            double totalAmount = orderQuantity * blush.getPrice();
            Orders order = new Orders(new Timestamp(new Date().getTime()), totalAmount, user);
            session.save(order);

            // Creating OrderDetails
            OrderDetails orderDetail = new OrderDetails(orderQuantity, blush.getPrice(), order, blush);
            session.save(orderDetail);

            transaction.commit();
            System.out.println(" E-Commerce Data Inserted Successfully!");

            //  Fetching and displaying inserted order details
            System.out.println("\n🔹 Order Summary:");
            System.out.println("Customer: " + user.getUsername());
            System.out.println("Product: " + blush.getName() + " | Price: " + blush.getPrice());
            System.out.println("Quantity: " + orderQuantity);
            System.out.println("Total Order Amount: $" + totalAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

