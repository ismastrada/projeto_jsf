package br.edu.unoesc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unoesc.dao.UfDao;
import br.edu.unoesc.model.Uf;

@FacesConverter(forClass = Uf.class, value = "ufConverter")
public class UfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent arg1,
			String arg2) {
		UfDao dao = (UfDao) facesContext
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(facesContext.getELContext(),
						"#{ufDaoImpl}", UfDao.class)
				.getValue(facesContext.getELContext());

		try {
			Long id = Long.valueOf(arg2);
			// buscar no banco o objeto com o id passado por parametro

			return dao.getById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Uf) {
			return ((Uf) arg2).getId().toString();
		}
		return "";
	}

}
