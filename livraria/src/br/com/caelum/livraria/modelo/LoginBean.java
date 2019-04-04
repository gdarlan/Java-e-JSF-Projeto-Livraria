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

		FacesContext context = FacesContext.getCurrentInstance();
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		} else {
			context.addMessage(null, new FacesMessage("Usuário não encontrado"));
			context.getExternalContext().getFlash();
			return "login?faces-redirect=true";
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

}
