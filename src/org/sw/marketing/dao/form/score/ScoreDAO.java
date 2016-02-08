package org.sw.marketing.dao.form.score;

import org.sw.marketing.data.form.Data.Score;

public interface ScoreDAO
{
	public long insertScore(long formID);
	
	public Score getScore(long scoreID);
	
	public java.util.List<Score> getScores(long formID);
	
	public void updateScore(Score score);
	
	public void deleteScore(long scoreID);
}
