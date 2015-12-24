package org.sw.marketing.dao.submission;

import org.sw.marketing.data.form.Data.Form.Question;
import org.sw.marketing.data.form.Data.Submission;
import org.sw.marketing.data.form.Data.Submission.Answer;

public interface SubmissionAnswerDAO
{
	public void insert(Submission submission, Answer answer, Question question);
	
	public java.util.List<Answer> getSubmissionAnswers(int submissionID);
}
