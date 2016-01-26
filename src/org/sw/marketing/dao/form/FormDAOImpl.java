package org.sw.marketing.dao.form;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.SQLStatements;
import org.sw.marketing.data.form.Data;
import org.sw.marketing.data.form.Data.Form;
import org.sw.marketing.data.form.Data.User;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class FormDAOImpl extends BaseDAO implements FormDAO
{
	@Override
	public List<Form> getForms(Data data)
	{
		java.util.List<Form> formList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_FORMS);
			statement.setLong(1, data.getUser().getId());
			resultSet = statement.executeQuery();

			Form form = null;
			while (resultSet.next())
			{
				if (formList == null)
				{
					formList = new java.util.ArrayList<Form>();
				}

				long id = resultSet.getLong("form_id");
				java.util.Date timestamp = resultSet.getTimestamp("form_creation_timestamp");
				String type = resultSet.getString("form_type");
				String title = resultSet.getString("form_title");
				String status = resultSet.getString("form_status");
				String prettyUrl = resultSet.getString("form_pretty_url");
				boolean deleted = resultSet.getBoolean("is_form_deleted");
				int submissionCount = resultSet.getInt("form_submission_count");
				String skinUrl = resultSet.getString("form_skin_url");
				String skinSelector = resultSet.getString("form_skin_selector");

				form = new Form();
				form.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				form.setId(id);
				form.setType(type);
				form.setTitle(title);
				form.setStatus(status);
				form.setPrettyUrl(prettyUrl);
				form.setDeleted(deleted);
				form.setSubmissionCount(submissionCount);
				form.setSkinUrl(skinUrl);
				form.setSkinSelector(skinSelector);

				formList.add(form);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return formList;
	}
	
	@Override
	public List<Form> getFormsSelfAssessment(Data data)
	{
		java.util.List<Form> formList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_FORMS_SELF_ASSESSMENT);
			statement.setLong(1, data.getUser().getId());
			resultSet = statement.executeQuery();

			Form form = null;
			while (resultSet.next())
			{
				if (formList == null)
				{
					formList = new java.util.ArrayList<Form>();
				}

				long id = resultSet.getLong("form_id");
				java.util.Date timestamp = resultSet.getTimestamp("form_creation_timestamp");
				String type = resultSet.getString("form_type");
				String title = resultSet.getString("form_title");
				String status = resultSet.getString("form_status");
				String prettyUrl = resultSet.getString("form_pretty_url");
				boolean deleted = resultSet.getBoolean("is_form_deleted");
				int submissionCount = resultSet.getInt("form_submission_count");
				String skinUrl = resultSet.getString("form_skin_url");
				String skinSelector = resultSet.getString("form_skin_selector");

				form = new Form();
				form.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				form.setId(id);
				form.setType(type);
				form.setTitle(title);
				form.setStatus(status);
				form.setPrettyUrl(prettyUrl);
				form.setDeleted(deleted);
				form.setSubmissionCount(submissionCount);
				form.setSkinUrl(skinUrl);
				form.setSkinSelector(skinSelector);

				formList.add(form);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return formList;
	}

	@Override
	public Form getForm(long id)
	{
		Form form = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_FORM);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				java.util.Date timestamp = resultSet.getTimestamp("form_creation_timestamp");
				java.util.Date startDate = resultSet.getDate("form_start_date");
				java.util.Date endDate = resultSet.getDate("form_end_date");
				String type = resultSet.getString("form_type");
				String status = resultSet.getString("form_status");
				String title = resultSet.getString("form_title");
				String prettyUrl = resultSet.getString("form_pretty_url");
				int submissionCount = resultSet.getInt("form_submission_count");
				String returnUrl = resultSet.getString("form_return_url");
				String skinUrl = resultSet.getString("form_skin_url");
				String skinSelector = resultSet.getString("form_skin_selector");				
				String publicFormIntroMessage = resultSet.getString("form_screen_public_form_intro");
				String publicFormClosingMessage = resultSet.getString("form_screen_public_form_closing");
				String messageThankYou = resultSet.getString("form_screen_thank_you");		
				String messageEnded = resultSet.getString("form_screen_ended");
				String messageMaxSubmitted = resultSet.getString("form_screen_max_submitted");
				String messageNotStarted = resultSet.getString("form_screen_not_started");	
				String messageOneSubmission = resultSet.getString("form_screen_one_submission");	
				int maxSubmissions = resultSet.getInt("form_max_submissions");
				boolean deleted = resultSet.getBoolean("is_form_deleted");

				form = new Form();
				form.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				form.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				form.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				form.setId(id);
				form.setType(type);
				form.setStatus(status);
				form.setTitle(title);
				form.setPrettyUrl(prettyUrl);
				form.setSubmissionCount(submissionCount);
				form.setReturnUrl(returnUrl);
				form.setSkinUrl(skinUrl);
				form.setSkinSelector(skinSelector);				
				form.setMessagePublicFormIntro(publicFormIntroMessage);
				form.setMessagePublicFormClosing(publicFormClosingMessage);
				form.setMessageThankYou(messageThankYou);
				form.setMessageEnded(messageEnded);
				form.setMessageMaxSubmitted(messageMaxSubmitted);
				form.setMessageNotStarted(messageNotStarted);
				form.setMessageOneSubmission(messageOneSubmission);		
				form.setMaxSubmissions(maxSubmissions);
				form.setDeleted(deleted);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return form;
	}

	@Override
	public Form getFormByPrettyUrl(String prettyUrl)
	{
		Form form = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_FORM_BY_PRETTY_URL);
			statement.setString(1, prettyUrl);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("form_id");
				java.util.Date timestamp = resultSet.getTimestamp("form_creation_timestamp");
				java.util.Date startDate = resultSet.getDate("form_start_date");
				java.util.Date endDate = resultSet.getDate("form_end_date");
				String type = resultSet.getString("form_type");
				String status = resultSet.getString("form_status");
				String title = resultSet.getString("form_title");
				int submissionCount = resultSet.getInt("form_submission_count");
				String returnUrl = resultSet.getString("form_return_url");
				String skinUrl = resultSet.getString("form_skin_url");
				String skinSelector = resultSet.getString("form_skin_selector");				
				String publicFormIntroMessage = resultSet.getString("form_screen_public_form_intro");
				String publicFormClosingMessage = resultSet.getString("form_screen_public_form_closing");
				String messageThankYou = resultSet.getString("form_screen_thank_you");		
				String messageEnded = resultSet.getString("form_screen_ended");
				String messageMaxSubmitted = resultSet.getString("form_screen_max_submitted");
				String messageNotStarted = resultSet.getString("form_screen_not_started");	
				String messageOneSubmission = resultSet.getString("form_screen_one_submission");				
				boolean deleted = resultSet.getBoolean("is_form_deleted");

				form = new Form();
				form.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				form.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				form.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				form.setId(id);
				form.setType(type);
				form.setStatus(status);
				form.setTitle(title);
				form.setPrettyUrl(prettyUrl);
				form.setSubmissionCount(submissionCount);
				form.setReturnUrl(returnUrl);
				form.setSkinUrl(skinUrl);
				form.setSkinSelector(skinSelector);				
				form.setMessagePublicFormIntro(publicFormIntroMessage);
				form.setMessagePublicFormClosing(publicFormClosingMessage);
				form.setMessageThankYou(messageThankYou);
				form.setMessageEnded(messageEnded);
				form.setMessageMaxSubmitted(messageMaxSubmitted);
				form.setMessageNotStarted(messageNotStarted);
				form.setMessageOneSubmission(messageOneSubmission);				
				form.setDeleted(deleted);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return form;
	}

	@Override
	public long createForm(User user)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_FORM, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, user.getId());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}

	@Override
	public long createFormSelfAssessment(User user)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_FORM_SELF_ASSESSMENT, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, user.getId());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}

	@Override
	public void deleteForm(long formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.DELETE_FORM);
			statement.setLong(1, formId);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}

	@Override
	public void updateForm(Form form)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.UPDATE_FORM);
			statement.setString(1, form.getTitle());
			statement.setString(2, form.getStatus());
			statement.setString(3, form.getPrettyUrl());
			statement.setString(4, form.getSkinUrl());
			statement.setString(5, form.getSkinSelector());
			statement.setString(6, form.getMessagePublicFormIntro());
			statement.setString(7, form.getMessagePublicFormClosing());
			statement.setString(8, form.getMessageThankYou());
			statement.setString(9, form.getMessageEnded());
			statement.setString(10, form.getMessageMaxSubmitted());
			statement.setString(11, form.getMessageNotStarted());
			statement.setString(12, form.getMessageOneSubmission());
			statement.setInt(13, form.getMaxSubmissions());
			
			java.util.Date startDate = form.getStartDate().toGregorianCalendar().getTime();
			java.sql.Date startDateSql = new java.sql.Date(startDate.getTime());
			statement.setDate(14, startDateSql);
			
			java.util.Date endDate = form.getEndDate().toGregorianCalendar().getTime();
			java.sql.Date endDateSql = new java.sql.Date(endDate.getTime());
			statement.setDate(15, endDateSql);
			statement.setLong(16, form.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}

	@Override
	public void updateFormSubmissionCount(long formID, int count)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.UPDATE_FORM_SUBMISSION_COUNT);
			statement.setInt(1, count);
			statement.setLong(2, formID);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}
}
