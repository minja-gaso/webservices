package org.sw.marketing.dao.form;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.SQLStatements;
import org.sw.marketing.data.form.Data.Form;
import org.sw.marketing.data.form.Data.User;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class FormDAOImpl extends BaseDAO implements FormDAO
{
	@Override
	public List<Form> getForms()
	{
		java.util.List<Form> formList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.Statement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLStatements.GET_FORMS);

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
				form.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp));
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
				String type = resultSet.getString("form_type");
				String status = resultSet.getString("form_status");
				String title = resultSet.getString("form_title");
				String prettyUrl = resultSet.getString("form_pretty_url");
				int submissionCount = resultSet.getInt("form_submission_count");
				String returnUrl = resultSet.getString("form_return_url");
				String skinUrl = resultSet.getString("form_skin_url");
				String skinSelector = resultSet.getString("form_skin_selector");
				boolean deleted = resultSet.getBoolean("is_form_deleted");

				form = new Form();
				form.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp));
				form.setId(id);
				form.setType(type);
				form.setStatus(status);
				form.setTitle(title);
				form.setPrettyUrl(prettyUrl);
				form.setSubmissionCount(submissionCount);
				form.setReturnUrl(returnUrl);
				form.setSkinUrl(skinUrl);
				form.setSkinSelector(skinSelector);
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
			statement.setString(2, form.getPrettyUrl());
			statement.setString(3, form.getSkinUrl());
			statement.setString(4, form.getSkinSelector());
			statement.setLong(5, form.getId());
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
