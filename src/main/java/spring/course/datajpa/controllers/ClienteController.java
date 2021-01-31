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
@SessionAttributes("cliente")
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
        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("title", "Detalle del cliente " + cliente.getName());
        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 4);

        //invocacion al service
        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

        model.addAttribute("title", "Listado de Clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);

        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("title", "Formulario de cliente");

        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente,
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
            if (cliente.getId() != null && cliente.getId() > 0 && cliente.getPhoto() != null && cliente.getPhoto().length() > 0) {
                uploadFileService.delete(cliente.getPhoto());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "Ha subido correctamente '" + uniqueFilename + "'");
            cliente.setPhoto(uniqueFilename);

        }
        String messageFlash = (cliente.getId() != null) ? "Cliente editado con éxito" : "Cliente creado con éxito";
        clienteService.save(cliente);
        status.setComplete(); //eliminará el objeto cliente de la session
        flash.addFlashAttribute("success", messageFlash);
        return "redirect:listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente;

        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "El id del cliente no existe en la DB");
                return "redirect:listar";
            }
        } else {
            flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
            return "redirect:listar";
        }
        model.put("cliente", cliente);
        model.put("title", "Editar de cliente");
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);

            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");

            if (uploadFileService.delete(cliente.getPhoto())) {
                flash.addFlashAttribute("info", "Foto " + cliente.getPhoto() + " eliminada con exito!");
            }

        }
        return "redirect:/listar";

    }


}
