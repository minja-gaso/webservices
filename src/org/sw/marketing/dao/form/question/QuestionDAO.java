package org.sw.marketing.dao.form.question;

import org.sw.marketing.data.form.Data.Form.Question;

public interface QuestionDAO
{
	/*
	 * create
	 */
	public void insertQuestion(int questionNumber, int page, long formId);
	public void insertQuestionSelfAssessment(int questionNumber, int page, long formId);
	
	/*
	 * read
	 */
	public java.util.List<Question> getQuestions(long formId);	
	public java.util.List<Question> getQuestionsByPage(long formId, int formPage);	
	public Question getQuestion(long questionId);
	public Question getQuestionByNumber(int number);
	/*
	 * update
	 */
	public void updateQuestion(Question question);
	/*
	 * delete
	 */
	public void deleteQuestion(long questionId);
	/*
	 * utility methods
	 */
	public int getNextNumber(long formId);
	public int getLatestPage(long formId);
	public int getMostRecentQuestionNumber(long formId);
	public int getQuestionPageCount(int page);
	public void moveDownQuestion(int questionNumber, long formId);
	public void moveUpQuestion(int questionNumber, long formId);
	public void incrementPageNumber(int questionNumber, long formId);
	public void decrementPageNumber(int questionNumber, long formId);
	
	public void insertPageBreak(int questionNumber, long formId);
	public void deletePageBreak(int pageNumber);
	public void removePageBreak(int questionNumber, long formId);
	public void moveDownQuestions(int questionNumber, long formId);
	public void moveUpQuestions(int questionNumber, long formId);
}
