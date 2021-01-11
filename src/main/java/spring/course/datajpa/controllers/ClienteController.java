package spring.course.datajpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.course.datajpa.models.dao.IClienteDao;
import spring.course.datajpa.models.entity.Cliente;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    //@Qualifier("clienteDaoJPA")  //si es unico se puede omitir el nombre clienteDaoJPA
    private IClienteDao clienteDao;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("title", "Listado de Clientes");
        model.addAttribute("clientes", clienteDao.findAll());

        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("title", "Formulario de cliente");

        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model){

        if (result.hasErrors()){
            //El objeto pasa de manera automatica si la clase se llama igual

            model.addAttribute("title", "Formulario de Cliente");
            return "form";
        }
        clienteDao.save(cliente);
        return "redirect:listar";
    }

}
