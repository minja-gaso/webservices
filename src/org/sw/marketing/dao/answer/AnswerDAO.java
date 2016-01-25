package org.sw.marketing.dao.answer;

import org.sw.marketing.data.form.Data;
import org.sw.marketing.data.form.Data.Form.Question.PossibleAnswer;

public interface AnswerDAO
{
	/*
	 * create
	 */
	public void insertAnswerToQuestion(long questionId, PossibleAnswer answer);
	public void insertAnswerToForm(long formID, PossibleAnswer answer);
	
	/*
	 * read
	 */
	public java.util.List<PossibleAnswer> getPossibleAnswers(long questionId);
	public java.util.List<Data.PossibleAnswer> getPossibleAnswersByForm(long formID);
	public PossibleAnswer getPossibleAnswer(long questionId);
	public PossibleAnswer getPossibleAnswerForForm(long questionId);
	public String getPossibleAnswerLabel(long answerID);
	/*
	 * update
	 */
	public void updatePossibleAnswer(PossibleAnswer answer);
	/*
	 * delete
	 */
	public void deleteAnswer(long answerID);
	public void deleteAnswerForForm(long answerID);
}
