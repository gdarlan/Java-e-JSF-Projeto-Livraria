package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		FacesContext currentInstance = FacesContext.getCurrentInstance();
		
		if (livro.getAutores().isEmpty()) {
			currentInstance.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));

		} else {
			if (this.livro.getId() == null) {

				new DAO<Livro>(Livro.class).adiciona(this.livro);
				currentInstance.addMessage(null, new FacesMessage("Livro gravado com sucesso!"));
			} else {
				new DAO<Livro>(Livro.class).atualiza(this.livro);
				currentInstance.addMessage(null, new FacesMessage("Livro alterado alterado com sucesso!"));
			}

			limpar();
		}

	}

	public void remover(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Livro removido com sucesso!"));
	}

	public void limpar() {
		livro = new Livro();

	}

	public void carregar(Livro livro) {
		System.out.println("carregando livro");
		this.livro = livro;
	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com 1!"));
		}
	}

}
