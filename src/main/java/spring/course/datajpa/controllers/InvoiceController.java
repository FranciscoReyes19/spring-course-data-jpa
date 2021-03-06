package spring.course.datajpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.course.datajpa.models.entity.Cliente;
import spring.course.datajpa.models.entity.Invoice;
import spring.course.datajpa.models.entity.Product;
import spring.course.datajpa.models.service.IClienteService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {

    @Autowired
    private IClienteService customerService;


    @GetMapping("/form/{customerId}")
    public String crear(@PathVariable(value = "customerId") Long customertId,
                        Map<String, Object> model,
                        RedirectAttributes flash){

        Cliente customer = customerService.findOne(customertId);
        if(customer == null ){
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        System.out.println("Esto sucede antes: "+customer.getName());

        Invoice invoice = new Invoice();
        invoice.setCliente(customer);
        System.out.println("Esto sucede getCliente: "+invoice.getCliente().getId());

        model.put("invoice", invoice);
        model.put("title", "Crear factura");

        return "invoice/form";
    }

    @GetMapping(value="/load_products/{term}", produces={ "application/json"})
    public @ResponseBody List<Product> loadProducts(@PathVariable String term){
        System.out.println("term:"+term);
        return customerService.findByName(term);
    }
}
