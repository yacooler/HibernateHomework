package ru.vyazankin.hiberlesson.repository;

import org.hibernate.Session;
import ru.vyazankin.hiberlesson.database.GlobalSessionFactory;
import ru.vyazankin.hiberlesson.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductDAO implements ProductRepository{

    @Override
    public Product findByID(Long id) {
        Session session = GlobalSessionFactory.get().getCurrentSession();
        return session.createQuery("select p from Product p where p.id = :id", Product.class)
                        .setParameter("id", id)
                        .getSingleResult();
    }

    @Override
    public List<Product> findAll() {
        Session session = GlobalSessionFactory.get().getCurrentSession();
        List<Product> result =
                session.createQuery("select p from Product p", Product.class)
                        .getResultList();
        return result;
    }

    @Override
    public void deleteByID(Long id) {
        Session session = GlobalSessionFactory.get().getCurrentSession();
        session.createQuery("delete from Product where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        Session session = GlobalSessionFactory.get().getCurrentSession();
        session.saveOrUpdate(product);
        return product;
    }

}
