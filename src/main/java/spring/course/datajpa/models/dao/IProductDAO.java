package spring.course.datajpa.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.course.datajpa.models.entity.Product;

import java.util.List;

public interface IProductDAO extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String term);
}
