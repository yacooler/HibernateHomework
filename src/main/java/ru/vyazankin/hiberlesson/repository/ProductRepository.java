package ru.vyazankin.hiberlesson.repository;

import org.hibernate.Session;
import ru.vyazankin.hiberlesson.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product findByID(Long id);
    List<Product> findAll();
    void deleteByID(Long id);
    Product saveOrUpdate(Product product);
}
