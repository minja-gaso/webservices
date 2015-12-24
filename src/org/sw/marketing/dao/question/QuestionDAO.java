package org.sw.marketing.dao.question;

import org.sw.marketing.data.form.Data.Form.Question;

public interface QuestionDAO
{
	/*
	 * create
	 */
	public int insertQuestion(int questionNumber, int page, int formId);
	
	/*
	 * read
	 */
	public java.util.List<Question> getQuestions(int formId);	
	public java.util.List<Question> getQuestionsByPage(int formId, int formPage);	
	public Question getQuestion(int questionId);
	public Question getQuestionByNumber(int number);
	/*
	 * update
	 */
	public void updateQuestion(Question question);
	/*
	 * delete
	 */
	public void deleteQuestion(int questionId);
	/*
	 * utility methods
	 */
	public int getNextNumber(int formId);
	public int getLatestPage(int formId);
	public int getMostRecentQuestionNumber(int formId);
	public int getQuestionPageCount(int page);
	public void moveDownQuestion(int questionNumber, int formId);
	public void moveUpQuestion(int questionNumber, int formId);
	public void incrementPageNumber(int questionNumber, int formId);
	public void decrementPageNumber(int questionNumber, int formId);
	
	public void insertPageBreak(int questionNumber, int formId);
	public void deletePageBreak(int pageNumber);
	public void removePageBreak(int questionNumber, int formId);
	public void moveDownQuestions(int questionNumber, int formId);
	public void moveUpQuestions(int questionNumber, int formId);
}
