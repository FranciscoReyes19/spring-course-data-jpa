package spring.course.datajpa.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import spring.course.datajpa.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}