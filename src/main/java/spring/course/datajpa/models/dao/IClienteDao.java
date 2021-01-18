package spring.course.datajpa.models.dao;

import spring.course.datajpa.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);

    void delete(Long id);
}
