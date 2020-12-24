package spring.course.datajpa.models.dao;

import spring.course.datajpa.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

    List<Cliente> findAll();
}
