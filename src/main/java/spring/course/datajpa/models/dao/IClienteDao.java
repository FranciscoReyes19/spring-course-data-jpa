package spring.course.datajpa.models.dao;

import org.springframework.data.repository.CrudRepository;
import spring.course.datajpa.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}