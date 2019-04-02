package br.com.caelum.livraria.modelo;

import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.bean.UsuarioDao;

@ManagedBean
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail() + " " + this.usuario.getSenha());

		boolean existe = new UsuarioDao().existe(this.usuario);

		if (existe) {
			return "livro?faces-redirect=true";
		}

		return null;
	}

}
