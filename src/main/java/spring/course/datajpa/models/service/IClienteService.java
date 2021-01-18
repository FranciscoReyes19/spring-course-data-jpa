package spring.course.datajpa.models.service;

import spring.course.datajpa.models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);

    void delete(Long id);
}
