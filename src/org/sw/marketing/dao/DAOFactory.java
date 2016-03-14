package org.sw.marketing.dao;

import org.sw.marketing.dao.form.FormDAO;
import org.sw.marketing.dao.form.FormDAOImpl;
import org.sw.marketing.dao.form.answer.AnswerDAO;
import org.sw.marketing.dao.form.answer.AnswerDAOImpl;
import org.sw.marketing.dao.form.question.QuestionDAO;
import org.sw.marketing.dao.form.question.QuestionDAOImpl;
import org.sw.marketing.dao.form.role.FormRoleDAO;
import org.sw.marketing.dao.form.role.FormRoleDAOImpl;
import org.sw.marketing.dao.form.score.ScoreDAO;
import org.sw.marketing.dao.form.score.ScoreDAOImpl;
import org.sw.marketing.dao.form.skin.FormSkinDAO;
import org.sw.marketing.dao.form.skin.FormSkinDAOImpl;
import org.sw.marketing.dao.form.submission.SubmissionAnswerDAO;
import org.sw.marketing.dao.form.submission.SubmissionAnswerDAOImpl;
import org.sw.marketing.dao.form.submission.SubmissionDAO;
import org.sw.marketing.dao.form.submission.SubmissionDAOImpl;
import org.sw.marketing.dao.form.submission.TempSubmissionAnswerDAO;
import org.sw.marketing.dao.form.submission.TempSubmissionAnswerDAOImpl;
import org.sw.marketing.dao.form.submission.TempSubmissionDAO;
import org.sw.marketing.dao.form.submission.TempSubmissionDAOImpl;
import org.sw.marketing.dao.form.user.UserDAO;
import org.sw.marketing.dao.form.user.UserDAOImpl;

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
	public static FormSkinDAO getFormSkinDAO()
	{
		return new FormSkinDAOImpl();
	}
	public static QuestionDAO getQuestionDAO()
	{
		return new QuestionDAOImpl();
	}
	public static AnswerDAO getPossibleAnswerDAO()
	{
		return new AnswerDAOImpl();
	}
	public static ScoreDAO getScoreDAO()
	{
		return new ScoreDAOImpl();
	}
	public static SubmissionDAO getSubmissionDAO()
	{
		return new SubmissionDAOImpl();
	}
	public static SubmissionAnswerDAO getSubmissionAnswerDAO()
	{
		return new SubmissionAnswerDAOImpl();
	}
	public static TempSubmissionDAO getTempSubmissionDAO()
	{
		return new TempSubmissionDAOImpl();
	}
	public static TempSubmissionAnswerDAO getTempSubmissionAnswerDAO()
	{
		return new TempSubmissionAnswerDAOImpl();
	}
	public static FormRoleDAO getFormRoleDAO(){
		return new FormRoleDAOImpl();
	}
}
