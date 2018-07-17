package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.modelo.Convidado;
import com.myapp.services.ConvidadoService;


/**
 * Controlador do Domínio Convidado
 * @author armandosoaressousa
 *
 */
@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoService service;
	
	/**
	 * Carrega a página com a lista de todos os convidados
	 * @param model Model da lista de convidados
	 * @return listaconvidados.html
	 */
	@RequestMapping("listaconvidados")
    public String listaConvidados(Model model){

        Iterable<Convidado> convidados = service.obterTodos();
        model.addAttribute("convidados", convidados);

        return "listaconvidados";
    }
	
	/**
	 * Salva um convidado na lista de convidados
	 * @param nome do convidado
	 * @param email do convidado
	 * @param telefone do convidado
	 * @param model model da lista de convidados
	 * @return redireciona para a página listaconvidados atualizada com o convidado salvo
	 */
	@RequestMapping(value= "salvar", method=RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
	                   @RequestParam("telefone") String telefone, Model model ){

	    Convidado novoConvidado = new Convidado(nome, email, telefone);
	    service.salvar(novoConvidado);
	    //new EmailService().enviar(email, "Lista VIP", "Olá " + nome + " você foi convidado para a lista VIP!", "armando@ufpi.edu.br");

	    Iterable<Convidado> convidados = service.obterTodos();
	    model.addAttribute("convidados", convidados);

	    return "redirect:listaconvidados";
	}
	
	/**
	 * Carrega o formulário com os dados populados do convidado selecionado para ser alterado
	 * @param id do convidado selecionado
	 * @param model da aplicação
	 * @return alteraconvidado que contem o formulário para alterar o convidado
	 */
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
	public String carregaFormularioAlterar(@PathVariable Long id, Model model){
		Convidado convidado = service.buscarConvidadoPorId(id);
		
		model.addAttribute("convidado", convidado);
		
		return "alteraconvidado";
	}
	
	/**
	 * Altera um convidado na lista de convidados
	 * @param id do convidado selecionado
	 * @param nome do convidado
	 * @param email do convidado
	 * @param telefone do convidado
	 * @param model model da lista de convidados
	 * @return redireciona para a página listaconvidados atualizada com o convidado salvo
	 */
	@RequestMapping(value= "alterar", method=RequestMethod.POST)
	public String processaAlterar(@RequestParam("id") Long id, @RequestParam("nome") String nome, 
			@RequestParam("email") String email, @RequestParam("telefone") String telefone, Model model ){
 
	    service.alterar(id, nome, email, telefone);
	    //new EmailService().enviar(email, "Lista VIP", "Olá " + nome + " você foi convidado para a lista VIP!", "armando@ufpi.edu.br");

	    Iterable<Convidado> convidados = service.obterTodos();
	    model.addAttribute("convidados", convidados);

	    return "redirect:listaconvidados";
	}
	
	/**
	 * Remove um convidado
	 * @param id do convidado selecionado
	 * @param model da aplicação
	 * @return listaconvidados atualizada
	 */
	@RequestMapping(value="/remover/{id}", method=RequestMethod.GET)
	public String processaRemover(@PathVariable Long id, Model model){
		service.remover(id);
		
		Iterable<Convidado> convidados = service.obterTodos();
		model.addAttribute("convidados", convidados);
		
		return "redirect:/listaconvidados";
	}

}