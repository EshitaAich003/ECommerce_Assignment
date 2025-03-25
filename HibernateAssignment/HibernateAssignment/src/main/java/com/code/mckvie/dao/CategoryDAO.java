package com.code.mckvie.dao;

import com.code.mckvie.model.Category;
import com.code.mckvie.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDAO {

    public void saveCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
    }

    public List<Category> getAllCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Category> categories = session.createQuery("FROM Category", Category.class).list();
        session.close();
        return categories;
    }

    public void updateCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(category);
        transaction.commit();
        session.close();
    }

    public void deleteCategory(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Category category = session.get(Category.class, id);
        if (category != null) {
            session.delete(category);
        }
        transaction.commit();
        session.close();
    }
}
