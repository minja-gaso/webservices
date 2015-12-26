package org.sw.marketing.dao.answer;

import org.sw.marketing.data.form.Data.Form.Question.PossibleAnswer;

public interface AnswerDAO
{
	/*
	 * create
	 */
	public void insertAnswerToQuestion(long questionId, PossibleAnswer answer);
	
	/*
	 * read
	 */
	public java.util.List<PossibleAnswer> getPossibleAnswers(long questionId);
	public String getPossibleAnswerLabel(long answerID);
}
