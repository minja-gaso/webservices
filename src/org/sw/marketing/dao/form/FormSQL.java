package org.sw.marketing.dao.form;

public class FormSQL
{
	/*
	 * create
	 */
	public static final String INSERT_USER = "INSERT INTO users (user_email, user_first_name, user_last_name, user_profile_image, is_user_active, is_user_admin) VALUES (?, ?, ?, ?, ?, ?)";

	public static final String INSERT_FORM = "INSERT INTO form.forms (fk_user_id) VALUES (?)";	
	public static final String INSERT_FORM_SELF_ASSESSMENT = "INSERT INTO form.forms (form_type, fk_user_id) VALUES ('self_assessment', ?)";	
	public static final String INSERT_QUESTION = "INSERT INTO form.questions (question_number, question_page, fk_form_id) VALUES (?, ?, ?)";
	public static final String INSERT_QUESTION_SELF_ASSESSMENT = "INSERT INTO form.questions (question_type, question_number, question_page, fk_form_id) VALUES ('radio', ?, ?, ?)";
	public static final String INSERT_ANSWER_TO_QUESTION = "INSERT INTO form.answers (answer_label, fk_question_id) VALUES (?, ?)";		
	public static final String INSERT_ANSWER_TO_FORM = "INSERT INTO form.form_answers (answer_label, answer_value, fk_form_id) VALUES (?, ?, ?)";	
	public static final String INSERT_SUBMISSION = "INSERT INTO form.submissions (fk_form_id) VALUES (?)";
	public static final String INSERT_SUBMISSION_ANSWER = "INSERT INTO form.submission_answers (sub_answer_value, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id) VALUES (?, ?, ?, ?)";
	public static final String INSERT_SUBMISSION_TEMP = "INSERT INTO form.temp_submissions (fk_form_id, session_id, ip_address) VALUES (?, ?, ?::inet)";
	public static final String INSERT_SUBMISSION_TEMP_ANSWER = "INSERT INTO form.temp_submission_answers (sub_answer_value, sub_page, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id) VALUES (?, ?, ?, ?, ?)";
	public static final String INSERT_SCORE = "INSERT INTO form.form_scores (fk_form_id) VALUES (?)";
	public static final String INSERT_ROLE = "INSERT INTO form.roles (role_type, role_email, fk_form_id) VALUES (?, ?, ?)";	
	
	/*
	 * read
	 */
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE user_email = ?";
	public static final String GET_FORMS = "SELECT * FROM form.forms WHERE form_type = 'survey' AND is_form_deleted = false AND fk_user_id = ?";
	public static final String GET_FORMS_SELF_ASSESSMENT = "SELECT * FROM form.forms WHERE form_type = 'self_assessment' AND is_form_deleted = false AND fk_user_id = ?";
	public static final String GET_FORM = "SELECT * FROM form.forms WHERE form_id = ?";
	public static final String GET_FORM_BY_PRETTY_URL = "SELECT * FROM form.forms WHERE form_pretty_url = ?";
	public static final String GET_QUESTIONS = "SELECT * FROM form.questions WHERE fk_form_id = ? ORDER BY question_number ASC";
	public static final String GET_QUESTIONS_BY_PAGE = "SELECT * FROM form.questions WHERE question_page = ? AND fk_form_id = ? ORDER BY question_number ASC";
	public static final String GET_QUESTION = "SELECT * FROM form.questions WHERE question_id = ?";
	public static final String GET_QUESTION_BY_NUMBER = "SELECT * FROM form.questions WHERE question_number = ?";
	public static final String GET_ANSWER = "SELECT * FROM form.answers WHERE answer_id = ?";
	public static final String GET_ANSWER_FOR_FORM = "SELECT * FROM form.form_answers WHERE answer_id = ?";
	public static final String GET_ANSWERS_FOR_QUESTION = "SELECT * FROM form.answers WHERE fk_question_id = ?";
	public static final String GET_ANSWERS_FOR_FORM = "SELECT * FROM form.form_answers WHERE fk_form_id = ? ORDER BY answer_value ASC";
	public static final String GET_SUBMISSION_TEMP = "SELECT * FROM form.temp_submissions WHERE fk_form_id = ? AND session_id = ?";
	public static final String GET_SUBMISSIONS = "SELECT * FROM form.submissions WHERE fk_form_id = ?";
	public static final String GET_SUBMISSIONS_FROM_START_TO_END_DATE = "SELECT * FROM form.submissions WHERE fk_form_id = ? AND submission_timestamp::date >= ? AND submission_timestamp::date <= ?";
	public static final String GET_SUBMISSION_ANSWERS = "SELECT * FROM form.submission_answers INNER JOIN form.questions ON submission_answers.fk_question_id = questions.question_id WHERE submission_answers.fk_submission_id = ?";
	public static final String GET_SUBMISSION_ANSWER = "SELECT * FROM form.submission_answers WHERE fk_submission_id = ? AND fk_question_id = ?";
	public static final String GET_SUBMISSION_ANSWER_BY_VALUE = "SELECT * FROM form.submission_answers WHERE fk_submission_id = ? AND sub_answer_value = ?";
	public static final String GET_SUBMISSION_TEMP_ANSWERS_BY_PAGE = "SELECT * FROM form.temp_submission_answers INNER JOIN form.questions ON temp_submission_answers.fk_question_id = questions.question_id WHERE temp_submission_answers.fk_submission_id = ? AND sub_page = ?";
	public static final String GET_SUBMISSION_TEMP_ANSWERS = "SELECT * FROM form.temp_submission_answers INNER JOIN form.questions ON temp_submission_answers.fk_question_id = questions.question_id WHERE temp_submission_answers.fk_submission_id = ?";
	public static final String GET_SCORE = "SELECT * FROM form.form_scores WHERE score_id = ?";
	public static final String GET_SCORES = "SELECT * FROM form.form_scores WHERE fk_form_id = ? ORDER BY score_range_begin ASC, score_range_end ASC";
	public static final String GET_SKINS = "SELECT * FROM skin.skins LEFT JOIN form.roles ON role_email = ? WHERE (role_type = 'admin' OR role_type = 'manager' OR fk_user_id = ?)";
	public static final String GET_SKIN = "SELECT * FROM skin.skins WHERE skin_id = ?";
	public static final String GET_FORM_ROLES = "SELECT * FROM form.roles WHERE fk_form_id = ?";
	public static final String GET_FORM_ROLE_UNIQUE_CHECK = "SELECT * FROM form.roles WHERE role_type = ? AND role_email = ? AND fk_form_id = ?";
	
	
	/*
	 * update
	 */
	public static final String UPDATE_QUESTION = "UPDATE form.questions SET question_number = ?, question_type = ?, question_label = ?, question_page = ?, question_default_value = ?, question_filter = ?, question_max_character_limit = ?, question_max_word_limit = ?, is_question_required = ?, question_header = ? WHERE question_id = ?";
	public static final String UPDATE_QUESTION_NUMBER = "UPDATE form.questions SET question_number = question_number - 1 WHERE question_id = ?";
	public static final String UPDATE_FORM = "UPDATE form.forms SET form_title = ?, form_status = ?, form_pretty_url = ?, fk_skin_id = ?, form_screen_public_form_intro = ?, form_screen_public_form_closing = ?, form_screen_thank_you = ?, form_screen_ended = ?, form_screen_max_submitted = ?, form_screen_not_started = ?, form_screen_one_submission = ?, form_max_submissions = ?, form_start_date = ?, form_end_date = ? WHERE form_id = ?";
	public static final String UPDATE_FORM_SUBMISSION_COUNT = "UPDATE form.forms SET form_submission_count = ? WHERE form_id = ?";
	public static final String UPDATE_POSSIBLE_ANSWER_FOR_FORM = "UPDATE form.form_answers SET answer_label = ?, answer_value = ? WHERE answer_id = ?";
	public static final String UPDATE_SCORE = "UPDATE form.form_scores SET score_range_begin = ?, score_range_end = ?, score_title = ?, score_summary = ? WHERE score_id = ?";
	
	/*
	 * delete
	 */
	public static final String DELETE_FORM = "UPDATE form.forms SET is_form_deleted = true WHERE form_id = ?";
	public static final String DELETE_QUESTION = "DELETE FROM form.questions WHERE question_id = ?";
	public static final String DELETE_PAGE_BREAK = "UPDATE form.questions SET question_page = question_page - 1 WHERE question_page >= ?";	
	public static final String DELETE_SUBMISSION_TEMP_ANSWERS = "DELETE FROM form.temp_submission_answers WHERE fk_submission_id = ? AND sub_page = ?";
	public static final String DELETE_ANSWER = "DELETE FROM form.answers WHERE answer_id = ?";
	public static final String DELETE_ANSWER_FOR_FORM = "DELETE FROM form.form_answers WHERE answer_id = ?";
	public static final String DELETE_SCORE = "DELETE FROM form.form_scores WHERE score_id = ?";
	public static final String DELETE_CALENDAR_ROLE = "DELETE FROM form.roles WHERE role_id = ?";
	
	/*
	 * copy to
	 */
	public static final String COPY_TO_SUBMISSIONS = "INSERT INTO form.submissions (submission_id, submission_timestamp, fk_form_id) SELECT submission_id, submission_timestamp, fk_form_id FROM form.temp_submissions WHERE session_id = ? AND fk_form_id = ?";
	public static final String DELETE_FROM_TEMP_SUBMISSIONS = "DELETE FROM form.temp_submissions WHERE session_id = ? AND fk_form_id = ?";
	public static final String COPY_TO_SUBMISSION_ANSWERS = "INSERT INTO form.submission_answers (sub_answer_id, sub_answer_value, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id) SELECT sub_answer_id, sub_answer_value, is_sub_answer_multiple_choice, fk_question_id, fk_submission_id FROM form.temp_submission_answers WHERE fk_submission_id = ?";
	public static final String DELETE_FROM_TEMP_SUBMISSION_ANSWERS = "DELETE FROM form.temp_submission_answers WHERE fk_submission_id = ?";
	
	/*
	 * utility statements
	 */
	public static final String GET_NEXT_NUMBER = "SELECT question_number FROM form.questions WHERE fk_form_id = ? ORDER BY question_number DESC LIMIT 1";
	public static final String GET_LATEST_PAGE = "SELECT question_page FROM form.questions WHERE fk_form_id = ? ORDER BY question_page DESC LIMIT 1";
	public static final String GET_MOST_RECENT_QUESTION_NUMBER = "SELECT COALESCE(MAX(question_number), 0) AS most_recent_question_number FROM form.questions WHERE fk_form_id = ?";
	public static final String GET_QUESTION_COUNT_FOR_PAGE = "SELECT count(*) AS question_count_for_page FROM form.questions WHERE question_page = ?";
	public static final String MOVE_DOWN_QUESTION = "UPDATE form.questions SET question_number = question_number + 1 WHERE question_id = ?";
	public static final String MOVE_UP_QUESTION = "UPDATE form.questions SET question_number = question_number - 1 WHERE question_id = ?";	
	public static final String DECREMENT_PAGE = "UPDATE form.questions SET question_page = question_page - 1 WHERE question_number = ?";
	public static final String INCREMENT_PAGE = "UPDATE form.questions SET question_page = question_page + 1 WHERE question_number = ?";
	
	/*
	 * page breaks
	 */
	public static final String INSERT_PAGE_BREAK = "UPDATE form.questions SET question_page = question_page + 1 WHERE question_number > ?";
	public static final String REMOVE_PAGE_BREAK = "UPDATE form.questions SET question_page = question_page - 1 WHERE question_number > ?";
	public static final String MOVE_DOWN_QUESTIONS = "UPDATE form.questions SET question_number = question_number + 1 WHERE question_number > ? AND fk_form_id = ?";
	public static final String MOVE_UP_QUESTIONS = "UPDATE form.questions SET question_number = question_number - 1 WHERE question_number > ? AND fk_form_id = ?";
}
