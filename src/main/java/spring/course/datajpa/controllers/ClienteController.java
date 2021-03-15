package spring.course.datajpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.course.datajpa.controllers.util.paginator.PageRender;
import spring.course.datajpa.models.entity.Cliente;
import spring.course.datajpa.models.service.IClienteService;
import spring.course.datajpa.models.service.IUploadFileService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

@Controller
@SessionAttributes("customers")
public class ClienteController {

    //@Qualifier("clienteDaoJPA")  //si es unico se puede omitir el nombre clienteDaoJPA
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> showPhoto(@PathVariable String filename) {

        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Cliente customer = clienteService.findOne(id);
        if (customer == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        model.put("customer", customer);
        model.put("title", "Detalle del cliente " + customer.getName());
        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 4);

        //invocacion al service
        Page<Cliente> customer = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar", customer);

        model.addAttribute("title", "Listado de Clientes");
        model.addAttribute("customers", customer);
        model.addAttribute("page", pageRender);

        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente customer = new Cliente();
        model.put("customer", customer);
        model.put("title", "Formulario de cliente");

        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente customer,
                          BindingResult result, Model model,
                          @RequestParam("file") MultipartFile photo,
                          RedirectAttributes flash,
                          SessionStatus status) {

        if (result.hasErrors()) {
            //El objeto pasa de manera automatica si la clase se llama igual

            model.addAttribute("title", "Formulario de Cliente");
            return "form";
        }


        if (!photo.isEmpty()) {
            if (customer.getId() != null && customer.getId() > 0 && customer.getPhoto() != null && customer.getPhoto().length() > 0) {
                uploadFileService.delete(customer.getPhoto());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "Ha subido correctamente '" + uniqueFilename + "'");
            customer.setPhoto(uniqueFilename);

        }
        String messageFlash = (customer.getId() != null) ? "Cliente editado con éxito" : "Cliente creado con éxito";
        clienteService.save(customer);
        status.setComplete(); //eliminará el objeto cliente de la session
        flash.addFlashAttribute("success", messageFlash);
        return "redirect:listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente customer;

        if (id > 0) {
            customer = clienteService.findOne(id);
            if (customer == null) {
                flash.addFlashAttribute("error", "El id del cliente no existe en la DB");
                return "redirect:listar";
            }
        } else {
            flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
            return "redirect:listar";
        }
        model.put("customer", customer);
        model.put("title", "Editar de cliente");
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente customer = clienteService.findOne(id);

            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");

            if(customer.getPhoto() != null && uploadFileService.delete(customer.getPhoto())){
                flash.addFlashAttribute("info", "Foto " + customer.getPhoto() + " eliminada con exito!");
            }else{
                System.out.println("sin foto!");
            }

        }else{
            System.out.println("Id vacio!");
        }
        return "redirect:/listar";

    }


}
