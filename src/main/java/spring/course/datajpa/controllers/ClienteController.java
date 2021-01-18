package spring.course.datajpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import spring.course.datajpa.models.entity.Cliente;
import spring.course.datajpa.models.service.IClienteService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    //@Qualifier("clienteDaoJPA")  //si es unico se puede omitir el nombre clienteDaoJPA
    private IClienteService clienteService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("title", "Listado de Clientes");
        model.addAttribute("clientes", clienteService.findAll());

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
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){

        if (result.hasErrors()){
            //El objeto pasa de manera automatica si la clase se llama igual

            model.addAttribute("title", "Formulario de Cliente");
            return "form";
        }
        clienteService.save(cliente);
        status.setComplete(); //eliminará el objeto cliente de la session
        return "redirect:listar";
    }
    @RequestMapping(value="/form/{id}")
    public String editar( @PathVariable(value = "id") Long id, Map<String, Object> model){

        Cliente cliente = null;

        if ( id > 0){
            cliente = clienteService.findOne(id);
        }else{
            return "redirect:listar";
        }
        model.put("cliente", cliente);
        model.put("title", "Editar de cliente");
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String delete( @PathVariable(value = "id") Long id ){
        if(id > 0){
            clienteService.delete(id);
        }
        return "redirect:/listar";

    }


}
