package org.sw.marketing.dao.form.submission;

import org.sw.marketing.data.form.Data.Submission;

public interface SubmissionDAO
{
	/*
	 * insert submission
	 */
	public long insert(long formID);
	
	/*
	 * read submissions
	 */
	public java.util.List<Submission> getSubmissions(long formID);
	public java.util.List<Submission> getSubmissionsFromStartToEndDate(long formID, String startDateStr, String endDateStr);
}
