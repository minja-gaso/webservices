package org.sw.marketing.dao.form;

import org.sw.marketing.data.form.Data;
import org.sw.marketing.data.form.Data.Form;
import org.sw.marketing.data.form.Data.User;

public interface FormDAO
{
	public java.util.List<Form> getForms(Data data);
	public java.util.List<Form> getFormsSelfAssessment(Data data);

	public Form getForm(long id);
	
	public Form getFormByPrettyUrl(String prettyUrl);

	public long createForm(User user);
	public long createFormSelfAssessment(User user);

	public void deleteForm(long formId);

	public void updateForm(Form form);
	public void updateFormSubmissionCount(long formID, int count);
}
