package com.myapp.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.modelo.Usuario;
import com.myapp.services.UsuarioService;
import com.myapp.util.FileSaver;
import com.myapp.util.GeradorSenha;
import com.myapp.util.ManipulaPermissoes;


@Controller
public class UsuarioController {
private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioService usuarioDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	/**
	 * Contrutor
	 */
	public UsuarioController(){
	}
	
	/**
	 * Faz a busca de usuários
	 * @param request HttpServletRequest do usuário 
	 * @param session HttpSession do usuário da aplicação
	 * @param model Model da aplicação
	 * @return jsp do ModelAndView da aplicação
	 * @throws ServletException exceção do tipo Servlet
	 * @throws IOException exceção do tipo IOException
	 */
	@RequestMapping(value="/buscarUsuario", method=RequestMethod.POST)
	protected String processarBusca(HttpServletRequest request, HttpSession session, Model model) {
		// recupera os dados passados pelo formulario de busca
		String tipo = request.getParameter("opcaotipo");
		String conteudo = request.getParameter("conteudobusca");

		//TODO implementar buscaPorConteudo no Controlador de Usuário
		//Usuario usuario = usuarioDAO.buscarPorConteudo(conteudo, tipo);

		/*
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
		} else {
			model.addAttribute("mensagem", "Não retornou nenhum resultado!");
		}
		*/
		return "/resultadoBusca";

	}
	
	/**
	 * Carrega o formulário de busca de usuários
	 * @param session Session do usuário da aplicação
	 * @return página TelaBuscarUsuario.jsp | Home.jsp
	 */
	@RequestMapping(value = "/buscarUsuario", method = RequestMethod.GET)
	public String carregaFormularioBusca(HttpSession session) {
		return "/buscarUsuario";
	}
	
	/**
	 * Lista os usuários da aplicação
	 * @param session Session do usuário da aplicação
	 * @param model Model da aplicação
	 * @return página TelaListarUsuarios.jsp | Home.jsp
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/listarUsuarios", method=RequestMethod.GET)
	public String processarListaUsuarios(HttpSession session, Model model) {
		List<Usuario> lista = usuarioDAO.listar();

		model.addAttribute("usuarios", lista);
		return "/listarUsuarios";
	}

	/**
	 * Faz a alteração dos dados de um usuário
	 * @param usuario Usuario da aplicação
	 * @param result BindingResult da aplicação para checar os erros
	 * @param session Session do usuário da aplicação
	 * @param redirect RedirectAttributes
	 * @return página TelaListarUsuarios.jsp | Home.jsp
	 * @throws ServletException trata a exceção ServletException caso aconteça
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/alterarUsuario", method=RequestMethod.POST)
	public String processarAlterarUsuario(String papel, MultipartFile imagem, Usuario usuario,
			HttpSession session, RedirectAttributes redirectAttribute) {
		String senhaOriginal;
		
		if (!imagem.isEmpty()) {
			String path = fileSaver.write("arquivos-imagem", imagem);
			usuario.setImagemPath(path);
		}

		senhaOriginal = usuario.getSenha();
		usuario.setSenha(new GeradorSenha().criptografa(senhaOriginal));
		usuario.setRoles(new ManipulaPermissoes().checaPapelUsuario(papel));

		usuarioDAO.alterar(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getLogin(), usuario.getSenha(), usuario.getImagemPath());
		redirectAttribute.addFlashAttribute("mensagem", "Usuário " + usuario.getId() + " alterado com sucesso!");
		return "redirect:/listarUsuarios";
	}

	/**
	 * Carrega o formulário Alterar Usuário
	 * @param session Session do usuário da aplicação 
	 * @return página TelaAlterarUsuario.jsp | Home.jsp
	 */
	@RequestMapping("/alterarUsuario")
	public ModelAndView carregaFormularioAlterar(int id, HttpSession session) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		ModelAndView mav = new ModelAndView("/alterarUsuario");
		mav.addObject("usuario", usuario);
		return (mav);
	}

	/**
	 * Carrega o formulário Inserir Usuário
	 * @param session Session do usuário da aplicação
	 * @return página TelaInserirUsuario.jsp | Home.jsp
	 */
	@RequestMapping(value="/inserirUsuario", method=RequestMethod.GET)
	public String carregarFormularioInserir(HttpSession session) {
		return "/inserirUsuario";
	}

	/**
	 * Insere usuário
	 * @param usuario Dados do Usuário
	 * @param result BindingResult que checa os erros de entrada da interface
	 * @param session Session do usuário da aplicação
	 * @param redirect RedirectAttributes mensagem de redirect
	 * @return página TelaPrincipal.jsp | Home.jsp
	 * @throws ServletException 
	 * @throws IOException
	 */
	@RequestMapping("/inserirUsuario")
	public String processarInserirUsuario(String papel, MultipartFile imagem, Usuario usuario, 
			HttpSession session, RedirectAttributes redirectAttribute){
		String senhaOriginal; 
			
		if (!imagem.isEmpty()) {
			String path = fileSaver.write("arquivos-imagem", imagem);
			usuario.setImagemPath(path);
		}

		senhaOriginal = usuario.getSenha();
		usuario.setSenha(new GeradorSenha().criptografa(senhaOriginal));
		usuario.setRoles(new ManipulaPermissoes().checaPapelUsuario(papel));
		 
		usuarioDAO.inserir(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuario inserido com sucesso!");
		return "redirect:/listarUsuarios";
	}
	
	/**
	 * Mostra detalhes do usuário selecionado
	 * @param id do usuário
	 * @param session do usuário logado na aplicação
	 * @return view TelaDetalhesUsuario.jsp | Home.jsp
	 */
	@RequestMapping("/detalharUsuario/{id}")
	public ModelAndView processarDetalhesUsuario(@PathVariable("id") Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("/detalhesUsuario");
		Usuario usuario = usuarioDAO.buscarPorId(id);
		mav.addObject("usuario", usuario);
		return mav;
	}

	/**
	 * Remove o usuáro seleconado
	 * @param id do usuário selecionado
	 * @param session do usuário logado na aplicação
	 * @param redirectAttribute mensagem do tipo flash para evitar repetição de post
	 * @return view listaUsuários | Home.jsp
	 */
	@RequestMapping("/removerUsuario/{id}")
	public String processarRemoverUsuario(@PathVariable("id") Integer id, HttpSession session,
			RedirectAttributes redirectAttribute) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		usuarioDAO.remover(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuario " + id + " removido com sucesso!");
		return "redirect:/listarUsuarios";
	}
	
	@RequestMapping("/meuperfil/{id}")
	public ModelAndView processarPerfilUsuario(@PathVariable("id") Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("/perfilUsuario");
		return mav;
	}

}