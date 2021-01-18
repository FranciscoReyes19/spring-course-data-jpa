package spring.course.datajpa.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.course.datajpa.models.dao.IClienteDao;
import spring.course.datajpa.models.entity.Cliente;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.delete(id);
    }
}
