package br.com.treinaweb.twclientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twclientes.model.Cliente;
import br.com.treinaweb.twclientes.repository.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired //dar controle ao spring sobre o atributo abaixo - injeção de dependencia da classe clienteRepository na classe ClienteController.
    private ClienteRepository clienteRepository;//atributo da classe clienteRepository

    //action principal listar
    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("cliente/listar.html");

        List<Cliente> clientes = clienteRepository.findAll();
        modelAndView.addObject("clientes", clientes);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/detalhar.html");

        Cliente cliente = clienteRepository.getOne(id);
        modelAndView.addObject("cliente", cliente);

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cliente");

        clienteRepository.deleteById(id);

        return modelAndView;
    }
    //action de cadastro de novo cliente
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cliente/formulario");

        modelAndView.addObject("cliente", new Cliente());//criando uma nova instancia vazia de cliente para ser preenchida de acordo com os valores da view

        return modelAndView;
    }
    //action que recebe os dados e então cadastra as informações no banco de dados e responde na pagina
    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cliente");

        clienteRepository.save(cliente);//recebe uma entidade que vai ser salva no BD

        return modelAndView;
    }
    //action editar
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/formulario");

        Cliente cliente = clienteRepository.getOne(id);
        modelAndView.addObject("cliente", cliente);

        return modelAndView;
    }
    //action editar que alterar os dados e retorna na pagina os valores alterados
    @PostMapping("/{id}/editar")
    public ModelAndView editar(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cliente");

        clienteRepository.save(cliente);

        return modelAndView;
    }
    
}