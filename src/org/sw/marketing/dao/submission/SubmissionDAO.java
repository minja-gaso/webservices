package org.sw.marketing.dao.submission;

import org.sw.marketing.data.form.Data.Submission;

public interface SubmissionDAO
{
	/*
	 * insert submission
	 */
	public int insert(int formID);
	
	/*
	 * read submissions
	 */
	public java.util.List<Submission> getSubmissions(int formID);
}
