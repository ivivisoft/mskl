-- DROP TABLE mskl_treat_plan;
CREATE TABLE mskl_treat_plan
(
  mskl_treatplan_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  mskl_medicine_id BIGINT NOT NULL,
  medical_name VARCHAR(32) DEFAULT '' NOT NULL,
  normal_name VARCHAR(32),
  medicine_unit VARCHAR(8),
  package_amount INT,
  user_id BIGINT,
  taken_amount DOUBLE(8,2),
  dose DOUBLE(8,2),
  daily_times INT,
  morning_alarm DATETIME,
  noon_alarm DATETIME,
  night_alarm DATETIME,
  update_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX mskl_plan_idx ON mskl_treat_plan (mskl_medicine_id);

-- DROP TABLE mskl_medicine;
CREATE TABLE mskl_medicine
(
  mskl_medicine_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  medical_name VARCHAR(128) DEFAULT '' NOT NULL,
  normal_name VARCHAR(32),
  manufacturer VARCHAR(50),
  medicine_unit VARCHAR(8),
  package_amount INT,
  dose DOUBLE(8,2),
  daily_times INT,
  med_code VARCHAR(18),
  bar_code VARCHAR(18),
  update_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- DROP TABLE mskl_treat_log;
CREATE TABLE mskl_treat_log
(
  mskl_treatlog_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  user_mobile VARCHAR(64),
  mskl_medicine_id BIGINT,
  medical_name VARCHAR(32),
  normal_name VARCHAR(32),
  set_alarm DATETIME,
  finish_at DATETIME,
  taken_status INT,
  taken_mood INT,
  taken_words VARCHAR(100),
  dose DOUBLE(8,2),
  update_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX treatlog_idx ON mskl_treat_log (user_mobile);

-- DROP TABLE mskl_medbox;
CREATE TABLE mskl_medbox
(
  user_id BIGINT NOT NULL,
  user_mobile VARCHAR(64),
  user_real_name VARCHAR(24),
  mskl_medicine_id BIGINT NOT NULL,
  medical_name VARCHAR(32),
  normal_name VARCHAR(32),
  total_amount INT NOT NULL,
  taken_amount DOUBLE(8,2),
  remaining_amount DOUBLE(8,2),
  dose DOUBLE(8,2),
  daily_times INT,
  start_day DATETIME,
  finish_day DATETIME,
  update_datetime DATETIME,
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX medbox_idx ON mskl_medbox (user_id);
CREATE INDEX medbox_idx1 ON mskl_medbox (user_mobile);
CREATE INDEX medbox_idx2 ON mskl_medbox (mskl_medicine_id);

-- DROP TABLE mskl_overseer;
CREATE TABLE mskl_overseer
(
  user_id BIGINT NOT NULL,
  user_mobile VARCHAR(64),
  overseer VARCHAR(24) DEFAULT '' NOT NULL,
  overseer_mobile VARCHAR(64) DEFAULT '' NOT NULL,
  update_datetime DATETIME,
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX overseer_idx ON mskl_overseer (user_id);

-- DROP TABLE mskl_push_msg;
CREATE TABLE mskl_push_msg
(
  push_msg_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  push_model CHAR(1),
  push_type VARCHAR(10),
  push_msg_kind CHAR(1),
  push_msg_title VARCHAR(100),
  push_msg_digest VARCHAR(500),
  push_extend VARCHAR(500),
  msg_from BIGINT,
  recv_user_id VARCHAR(32),
  html_url VARCHAR(255),
  create_datetime DATETIME,
  is_read CHAR(1),
  business_type CHAR(2)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX push_msg_idx ON mskl_push_msg (recv_user_id, push_type);

-- DROP TABLE mskl_feedback;
CREATE TABLE mskl_feedback
(
  feedback_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  user_name VARCHAR(32),
  user_mobile VARCHAR(64),
  feedback_msg VARCHAR(500),
  update_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP TABLE mskl_promotion_info;
CREATE TABLE mskl_promotion_info
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  promotion_name VARCHAR(64) DEFAULT '' NOT NULL,
  info_channel VARCHAR(10),
  info_title VARCHAR(64) DEFAULT '' NOT NULL,
  info_content LONGTEXT,
  banner_url VARCHAR(255) DEFAULT '' NOT NULL,
  content_url VARCHAR(255),
  sort DECIMAL(2),
  create_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX info_idx ON mskl_promotion_info (info_channel, create_datetime);

-- DROP TABLE mskl_user_bankcard;
CREATE TABLE mskl_user_bankcard
(
  user_id BIGINT NOT NULL,
  bank_no VARCHAR(6),
  bank_name VARCHAR(100),
  is_default CHAR(1),
  bank_addr_no VARCHAR(4),
  card_no VARCHAR(24),
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX bank_idx ON mskl_user_bankcard (user_id);

-- DROP TABLE mskl_user_cashout_application;
CREATE TABLE mskl_user_cashout_application
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  bank_no VARCHAR(6),
  bank_name VARCHAR(100),
  amount BIGINT,
  bank_addr_no VARCHAR(4),
  application_datetime DATETIME,
  review_status CHAR(1),
  pay_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX cashout_idx ON mskl_user_cashout_application (user_id);

CREATE TABLE mskl_file
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  mobile VARCHAR(16),
  file_path DOUBLE(8,2),
  type INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_user_cashout_application
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  bank_no VARCHAR(6),
  bank_name VARCHAR(100),
  amount BIGINT,
  bank_addr_no VARCHAR(4),
  application_datetime DATETIME,
  review_status CHAR(1),
  pay_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX cashout_idx ON mskl_user_cashout_application (user_id);


CREATE TABLE mskl_user_bankcard
(
    user_id BIGINT NOT NULL,
    bank_no VARCHAR(6),
    bank_name VARCHAR(100),
    is_default CHAR(1),
    bank_addr_no VARCHAR(4),
    card_no VARCHAR(24),
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX bank_idx ON mskl_user_bankcard (user_id);

CREATE TABLE mskl_user_trade
(
    user_id BIGINT NOT NULL,
    error_count INT DEFAULT 0,
    user_trade_pwd VARCHAR(32) DEFAULT '' NOT NULL,
    user_trade_pwd_strength CHAR(1) DEFAULT '' NOT NULL,
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB DEFAULT CHARSET=utf8;











