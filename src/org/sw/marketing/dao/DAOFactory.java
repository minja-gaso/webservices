package org.sw.marketing.dao;

import org.sw.marketing.dao.answer.AnswerDAO;
import org.sw.marketing.dao.answer.AnswerDAOImpl;
import org.sw.marketing.dao.form.FormDAO;
import org.sw.marketing.dao.form.FormDAOImpl;
import org.sw.marketing.dao.question.QuestionDAO;
import org.sw.marketing.dao.question.QuestionDAOImpl;
import org.sw.marketing.dao.submission.SubmissionAnswerDAO;
import org.sw.marketing.dao.submission.SubmissionAnswerDAOImpl;
import org.sw.marketing.dao.submission.SubmissionDAO;
import org.sw.marketing.dao.submission.SubmissionDAOImpl;
import org.sw.marketing.dao.user.UserDAO;
import org.sw.marketing.dao.user.UserDAOImpl;

public class DAOFactory
{
	public static UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}
	public static FormDAO getFormDAO()
	{
		return new FormDAOImpl();
	}
	public static QuestionDAO getQuestionDAO()
	{
		return new QuestionDAOImpl();
	}
	public static AnswerDAO getPossibleAnswerDAO()
	{
		return new AnswerDAOImpl();
	}
	public static SubmissionDAO getSubmissionDAO()
	{
		return new SubmissionDAOImpl();
	}
	public static SubmissionAnswerDAO getSubmissionAnswerDAO()
	{
		return new SubmissionAnswerDAOImpl();
	}
}
