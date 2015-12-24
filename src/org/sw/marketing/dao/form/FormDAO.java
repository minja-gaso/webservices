package org.sw.marketing.dao.form;

import org.sw.marketing.data.form.Data.Form;

public interface FormDAO
{
	public java.util.List<Form> getForms();
	
	public Form getForm(int id);
	
	public int createForm();

	public void deleteForm(int formId);
	
	public void updateForm(Form form);
}
