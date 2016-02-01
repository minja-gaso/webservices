package org.sw.marketing.dao.submission;

import org.sw.marketing.data.form.Data.Submission;

public interface TempSubmissionDAO
{
	/*
	 * insert submission
	 */
	public long insert(long formID, String sessionID, String IP_ADDRESS);
	
	/*
	 * read submissions
	 */
	public Submission getSubmissionBySessionID(long formID, String SESSION_ID);
	
	/*
	 * move to submitted table and delete from temp
	 */
	public void copyTo(String SESSION_ID, long formID);
	public void deleteFromTemp(String SESSION_ID, long formID);
}
