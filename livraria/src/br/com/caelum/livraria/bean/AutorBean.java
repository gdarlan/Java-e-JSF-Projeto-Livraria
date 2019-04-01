package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
			return "livro?faces-redirect=true";
		} else {
			new DAO<Autor>(Autor.class).atualiza(autor);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alteração efetuada com sucesso!"));
			limpar();
			return null;
		}
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void remover(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}

	public void limpar() {
		this.autor = new Autor();

	}
}
