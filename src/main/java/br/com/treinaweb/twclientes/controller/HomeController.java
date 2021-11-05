package br.com.treinaweb.twclientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    // action home
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // action mensagem
    /*
     * Passa mensagem da camada de controle para a view atraves do Model é uma
     * interface public String mensagem(Model model) {
     * model.addAttribute("mensagem", "Mensagem vinda do servidor!"); return
     * "mensagem"; }
     */
    // ModelMap com outro model - que nesse caso é a implementação da interface
    // model
    /*
     * @GetMapping("/mensagem") public String mensagem(ModelMap modelMap) {
     * modelMap.addAttribute("mensagem", "Outra Mensagem vinda do servidor!");
     * return "mensagem"; }
     */
    // Passa mensagem da camada de controle para a view com o modelandview
    @GetMapping("/mensagem")
    public ModelAndView mensagem() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("mensagem");
        modelAndView.addObject("mensagem", "Mensagem vinda do servidor pelo ModelAndView");

        return modelAndView;

        // forma mais organizada que guarda os objetos e mapeia qual view vai ser
        // utilizada, usar quando uma action seja muito grande.
    }

    /*
     * Ajuda nas buscas e passagem de informação da view para o controller duas
     * formas atraves do PathVariable e RequestParam
     * 
     * PathVariable = variavel na rota - segunda parte da rota é variavel.
     * 
     * @GetMapping("/saudacao/{nome}") public ModelAndView saudacao(@PathVariable
     * String nome) { ModelAndView modelAndView = new ModelAndView();
     * 
     * modelAndView.setViewName("saudacao"); modelAndView.addObject("nome", nome);
     * 
     * return modelAndView; } resultado = http://localhost:8080/saudacao/Treinaweb
     */

    // outra forma dessa vez com o RequestParam - paramentro na requisição
    @GetMapping("/saudacao")
    public ModelAndView saudacao(@RequestParam(required = false, defaultValue = "TreinaWeb") String nome) {// sana o
                                                                                                           // erro 400
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("saudacao");
        modelAndView.addObject("nome", nome);

        return modelAndView;
        // http://localhost:8080/saudacao?nome=Julio - parametro na rota.
    }
}
