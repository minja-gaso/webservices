package org.sw.marketing.dao;

public class SQLStatements
{
	/*
	 * create
	 */
	public static final String INSERT_USER = "INSERT INTO users (user_email, user_first_name, user_last_name, user_profile_image, is_user_active, is_user_admin) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String INSERT_FORM = "INSERT INTO forms (fk_user_id) VALUES (?)";	
	public static final String INSERT_QUESTION = "INSERT INTO questions (question_number, question_page, fk_form_id) VALUES (?, ?, ?)";
	public static final String INSERT_ANSWER_TO_QUESTION = "INSERT INTO answers (answer_label, fk_question_id) VALUES (?, ?)";	
	public static final String INSERT_SUBMISSION = "INSERT INTO submissions (fk_form_id) VALUES (?)";
	public static final String INSERT_SUBMISSION_ANSWER = "INSERT INTO submission_answers (sub_answer_value, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id) VALUES (?, ?, ?, ?)";
	public static final String INSERT_SUBMISSION_TEMP = "INSERT INTO temp_submissions (fk_form_id, session_id) VALUES (?, ?)";
	public static final String INSERT_SUBMISSION_TEMP_ANSWER = "INSERT INTO temp_submission_answers (sub_answer_value, sub_page, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id) VALUES (?, ?, ?, ?, ?)";
	
	/*
	 * read
	 */
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE user_email = ?";
	public static final String GET_FORMS = "SELECT * FROM forms WHERE is_form_deleted = false";
	public static final String GET_FORM = "SELECT * FROM forms WHERE form_id = ?";
	public static final String GET_FORM_BY_PRETTY_URL = "SELECT * FROM forms WHERE form_pretty_url = ?";
	public static final String GET_QUESTIONS = "SELECT * FROM questions WHERE fk_form_id = ? ORDER BY question_number ASC";
	public static final String GET_QUESTIONS_BY_PAGE = "SELECT * FROM questions WHERE question_page = ? AND fk_form_id = ? ORDER BY question_number ASC";
	public static final String GET_QUESTION = "SELECT * FROM questions WHERE question_id = ?";
	public static final String GET_QUESTION_BY_NUMBER = "SELECT * FROM questions WHERE question_number = ?";
	public static final String GET_ANSWER = "SELECT * FROM answers WHERE answer_id = ?";
	public static final String GET_ANSWERS_FOR_QUESTION = "SELECT * FROM answers WHERE fk_question_id = ?";
	public static final String GET_SUBMISSION_TEMP = "SELECT * FROM temp_submissions WHERE fk_form_id = ? AND session_id = ?";
	public static final String GET_SUBMISSIONS = "SELECT * FROM submissions WHERE fk_form_id = ?";
	public static final String GET_SUBMISSIONS_FROM_START_TO_END_DATE = "SELECT * FROM submissions WHERE fk_form_id = ? AND submission_timestamp::date >= ? AND submission_timestamp::date <= ?";
	public static final String GET_SUBMISSION_ANSWERS = "SELECT * FROM submission_answers INNER JOIN questions ON submission_answers.fk_question_id = questions.question_id WHERE submission_answers.fk_submission_id = ?";
	public static final String GET_SUBMISSION_ANSWER = "SELECT * FROM submission_answers WHERE fk_submission_id = ? AND fk_question_id = ?";
	public static final String GET_SUBMISSION_ANSWER_BY_VALUE = "SELECT * FROM submission_answers WHERE fk_submission_id = ? AND sub_answer_value = ?";
	public static final String GET_SUBMISSION_TEMP_ANSWERS = "SELECT * FROM temp_submission_answers INNER JOIN questions ON temp_submission_answers.fk_question_id = questions.question_id WHERE temp_submission_answers.fk_submission_id = ? AND sub_page = ?";
	
	/*
	 * update
	 */
	public static final String UPDATE_QUESTION = "UPDATE questions SET question_number = ?, question_type = ?, question_label = ?, question_page = ?, question_default_value = ?, question_filter = ?, question_max_character_limit = ?, question_max_word_limit = ?, is_question_required = ? WHERE question_id = ?";
	public static final String UPDATE_QUESTION_NUMBER = "UPDATE questions SET question_number = question_number - 1 WHERE que WHERE question_id = ?";
	public static final String UPDATE_FORM = "UPDATE forms SET form_title = ?, form_pretty_url = ?, form_skin_url = ?, form_skin_selector = ? WHERE form_id = ?";
	
	/*
	 * delete
	 */
	public static final String DELETE_FORM = "UPDATE forms SET is_form_deleted = true WHERE form_id = ?";
	public static final String DELETE_QUESTION = "DELETE FROM questions WHERE question_id = ?";
	public static final String DELETE_PAGE_BREAK = "UPDATE questions SET question_page = question_page - 1 WHERE question_page >= ?";	
	public static final String DELETE_SUBMISSION_TEMP_ANSWERS = "DELETE FROM temp_submission_answers WHERE fk_submission_id = ? AND sub_page = ?";
	
	/*
	 * copy to
	 */
	public static final String COPY_TO_SUBMISSIONS = "INSERT INTO submissions (submission_id, submission_timestamp, fk_form_id) SELECT submission_id, submission_timestamp, fk_form_id FROM temp_submissions WHERE session_id = ? AND fk_form_id = ?";
	public static final String DELETE_FROM_TEMP_SUBMISSIONS = "DELETE FROM temp_submissions WHERE session_id = ? AND fk_form_id = ?";
	public static final String COPY_TO_SUBMISSION_ANSWERS = "INSERT INTO submission_answers (sub_answer_id, sub_answer_value, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id) SELECT sub_answer_id, sub_answer_value, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id FROM temp_submission_answers WHERE fk_submission_id = ?";
	public static final String DELETE_FROM_TEMP_SUBMISSION_ANSWERS = "DELETE FROM temp_submission_answers WHERE fk_submission_id = ?";
	
	/*
	 * utility statements
	 */
	public static final String GET_NEXT_NUMBER = "SELECT question_number FROM questions WHERE fk_form_id = ? ORDER BY question_number DESC LIMIT 1";
	public static final String GET_LATEST_PAGE = "SELECT question_page FROM questions WHERE fk_form_id = ? ORDER BY question_page DESC LIMIT 1";
	public static final String GET_MOST_RECENT_QUESTION_NUMBER = "SELECT COALESCE(MAX(question_number), 0) AS most_recent_question_number FROM questions WHERE fk_form_id = ?";
	public static final String GET_QUESTION_COUNT_FOR_PAGE = "SELECT count(*) AS question_count_for_page FROM questions WHERE question_page = ?";
	public static final String MOVE_DOWN_QUESTION = "UPDATE questions SET question_number = question_number + 1 WHERE question_id = ?";
	public static final String MOVE_UP_QUESTION = "UPDATE questions SET question_number = question_number - 1 WHERE question_id = ?";	
	public static final String DECREMENT_PAGE = "UPDATE questions SET question_page = question_page - 1 WHERE question_number = ?";
	public static final String INCREMENT_PAGE = "UPDATE questions SET question_page = question_page + 1 WHERE question_number = ?";
	
	/*
	 * page breaks
	 */
	public static final String INSERT_PAGE_BREAK = "UPDATE questions SET question_page = question_page + 1 WHERE question_number > ?";
	public static final String REMOVE_PAGE_BREAK = "UPDATE questions SET question_page = question_page - 1 WHERE question_number > ?";
	public static final String MOVE_DOWN_QUESTIONS = "UPDATE questions SET question_number = question_number + 1 WHERE question_number > ? AND fk_form_id = ?";
	public static final String MOVE_UP_QUESTIONS = "UPDATE questions SET question_number = question_number - 1 WHERE question_number > ? AND fk_form_id = ?";
}
