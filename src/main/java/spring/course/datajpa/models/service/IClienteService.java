package spring.course.datajpa.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.course.datajpa.models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    void save(Cliente customer);

    Cliente findOne(Long id);

    void delete(Long id);
}
