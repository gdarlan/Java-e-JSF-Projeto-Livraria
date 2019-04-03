package br.com.caelum.livraria.modelo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.bean.UsuarioDao;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public String efetuaLogin() {
		System.out.println(
				"Fazendo login do usuário " + this.getUsuario().getEmail() + " " + this.getUsuario().getSenha());

		boolean existe = new UsuarioDao().existe(this.getUsuario());

		if (existe) {
			return "livro?faces-redirect=true";
		}else {
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro"));
			return null;
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
