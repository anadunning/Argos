package br.edu.ifpb.argos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.argos.entity.Pessoa;

@FacesConverter(value = "pessoaConverter")
public class PessoaConverter extends SelectItemsBaseConverter {

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Pessoa)
			return ((Pessoa) value).getId().toString();
		else
			return null;
	}
}