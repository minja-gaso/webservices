package org.sw.marketing.dao.form.submission;

import org.sw.marketing.data.form.Data.Form.Question;
import org.sw.marketing.data.form.Data.Submission;
import org.sw.marketing.data.form.Data.Submission.Answer;

public interface TempSubmissionAnswerDAO
{
	public void insert(Submission submission, Answer answer, Question question);

	public java.util.List<Answer> getSubmissionAnswersByPage(Submission submission);
	
	public java.util.List<Answer> getSubmissionAnswers(Submission submission);
	
	public void deleteSubmissionAnswersByPage(Submission submission);
	
	/*
	 * move to submitted table
	 */
	public void copyTo(Submission submission);
	public void deleteFromTemp(Submission submission);
}
