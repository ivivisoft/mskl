#初始化建表语句

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
  normal_name VARCHAR(256),
  manufacturer VARCHAR(128),
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
  medical_name VARCHAR(128),
  normal_name VARCHAR(256),
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
  user_real_name VARCHAR(64),
  mskl_medicine_id BIGINT NOT NULL,
  medical_name VARCHAR(128),
  normal_name VARCHAR(256),
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
  overseer VARCHAR(64) DEFAULT '' NOT NULL,
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
  business_type CHAR(2),
  treat_log_id BIGINT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX mskl_push_msg_treat_log_id_uindex ON mskl_push_msg (treat_log_id);
CREATE INDEX push_msg_idx ON mskl_push_msg (recv_user_id, push_type);

-- DROP TABLE mskl_feedback;
CREATE TABLE mskl_feedback
(
  feedback_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  user_name VARCHAR(32),
  user_mobile VARCHAR(64),
  feedback_msg VARCHAR(512),
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


CREATE TABLE mskl_user_bankcard
(
    user_id BIGINT NOT NULL,
    bank_no VARCHAR(6),
    bank_name VARCHAR(128),
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


CREATE TABLE mskl_account
(
  mskl_account_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20) NOT NULL,
  user_real_name VARCHAR(64),
  currency_type VARCHAR(3),
  avalaible_amount BIGINT(20),
  freeze_amount BIGINT(20),
  account_status VARCHAR(2),
  md5_code VARCHAR(32),
  remark VARCHAR(255),
  create_datetime DATETIME,
  update_datetime DATETIME,
  version BIGINT(20),
  hash VARCHAR(32),
  account_status_reason VARCHAR(2)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX account_idx ON mskl_account (user_id);

CREATE TABLE mskl_account_jour
(
  id BIGINT(20) NOT NULL,
  mskl_account_id BIGINT(20) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  trans_datetime DATETIME,
  account_biz_type VARCHAR(2),
  trans_amount BIGINT(20),
  pre_amount BIGINT(20),
  post_amount BIGINT(20),
  seq_flag VARCHAR(2),
  ref_serial_no VARCHAR(20),
  remark VARCHAR(255),
  work_date VARCHAR(8),
  version BIGINT(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX account_jour_idx ON mskl_account_jour (mskl_account_id, user_id);
CREATE INDEX account_jour_idx1 ON mskl_account_jour (account_biz_type);


CREATE TABLE mskl_auth_login
(
  id BIGINT(20) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  app_type INT(11),
  app_user_id VARCHAR(64) NOT NULL,
  access_token VARCHAR(64),
  token_expire DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_bank_type
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  bank_no VARCHAR(6),
  bank_name VARCHAR(128),
  is_enable CHAR(1),
  bank_addr_no VARCHAR(4),
  is_quick_pay CHAR(1),
  channel_id BIGINT(20),
  rates DECIMAL(8,5)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_channel
(
  channel_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  channel_name VARCHAR(32) NOT NULL,
  create_time DATETIME NOT NULL,
  channel_code VARCHAR(19) NOT NULL,
  create_by BIGINT(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_channel_bank
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  bank_no VARCHAR(6),
  bank_code VARCHAR(12),
  is_quick_pay CHAR(1),
  channel_id BIGINT(20),
  rates DECIMAL(8,5),
  level DECIMAL(2)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX channel_bank_idx ON mskl_channel_bank (bank_no, channel_id);

CREATE TABLE mskl_credits
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  bank_no VARCHAR(6),
  service_note VARCHAR(512),
  service_class VARCHAR(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_default_moodmsg
(
  mood_msg_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  mood BIGINT(20),
  mood_msg VARCHAR(8)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_disease
(
  mskl_disease_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  disease_name VARCHAR(128) NOT NULL,
  edit_by VARCHAR(32),
  update_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_email_checkcode
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  email VARCHAR(120),
  email_biz_type VARCHAR(2),
  check_code VARCHAR(32),
  checkcode_set_datetime INT(11),
  last_check_datetime INT(11),
  invalid_datetime INT(11),
  check_times INT(11)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX email_checkcode_idx ON mskl_email_checkcode (email, email_biz_type);



CREATE TABLE mskl_identity
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20),
  user_name VARCHAR(32),
  user_real_name VARCHAR(64),
  med_record_photo1 VARCHAR(256),
  med_record_photo2 VARCHAR(256),
  med_record_photo3 VARCHAR(256),
  id_photo VARCHAR(256),
  idcard VARCHAR(18),
  id_status CHAR(1),
  med_record_status CHAR(1),
  id_review_datetime DATETIME,
  med_recd_review_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE mskl_mod_privilege
(
  mp_id BIGINT(20),
  module_code VARCHAR(32),
  pri_code VARCHAR(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_msg_recv_log
(
  push_msg_id BIGINT(20) NOT NULL,
  recv_user_id BIGINT(20) NOT NULL,
  is_read CHAR(1),
  is_delete CHAR(1),
  update_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX msg_log_idx ON mskl_msg_recv_log (recv_user_id);



CREATE TABLE mskl_pay_channel
(
  channel_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  channel_name VARCHAR(64),
  channel_status CHAR(1),
  merchant_id VARCHAR(64),
  channel_version VARCHAR(10),
  sign_type VARCHAR(4),
  poundage_type VARCHAR(10),
  business_webgateway VARCHAR(100),
  business_wapgateway VARCHAR(100),
  sign_key VARCHAR(100),
  cer_path VARCHAR(1000),
  remark VARCHAR(256),
  business_filegateway VARCHAR(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_privilege
(
  pri_id BIGINT(20),
  pri_code VARCHAR(32),
  pri_name VARCHAR(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE mskl_promotion_signup
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  promotion_id BIGINT(20) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  user_name VARCHAR(32),
  mobile VARCHAR(64) NOT NULL,
  status CHAR(1),
  task_complete_days DECIMAL(4),
  reward BIGINT(20),
  signup_datetime DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX sign_idx ON mskl_promotion_signup (promotion_id, user_id);

CREATE TABLE mskl_push_msg_content
(
  push_msg_id BIGINT(20) NOT NULL,
  msg_content VARCHAR(4000)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_role_privilege
(
  rp_id BIGINT(20),
  role_code VARCHAR(32),
  mp_id VARCHAR(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_scores_exchange
(
  order_no BIGINT(20) PRIMARY KEY NOT NULL,
  user_id BIGINT(20),
  mskl_account_id BIGINT(20),
  account_biz_type VARCHAR(2),
  seq_flag VARCHAR(2),
  trans_amount BIGINT(20),
  pre_amount BIGINT(20),
  post_amount BIGINT(20),
  verify_status VARCHAR(2),
  create_datetime DATETIME,
  update_datetime DATETIME,
  verify_datetime DATETIME,
  verify_user VARCHAR(20),
  oper_user VARCHAR(20),
  exchange_rule_id VARCHAR(2),
  score_to_money BIGINT(20),
  need_audit VARCHAR(2),
  remark VARCHAR(255),
  ref_serial_no VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_sms_checkcode
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  mobile VARCHAR(64) NOT NULL,
  sms_biz_type VARCHAR(2) NOT NULL,
  check_code VARCHAR(32),
  checkcode_set_datetime DATETIME,
  last_check_datetime DATETIME,
  invalid_datetime DATETIME,
  check_times INT(11)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX sms_checkcode_idx ON mskl_sms_checkcode (mobile, sms_biz_type);

CREATE TABLE mskl_sys_module
(
  mod_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  module_code VARCHAR(32),
  module_name VARCHAR(32),
  parent_code VARCHAR(32),
  module_url VARCHAR(256)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_sys_role
(
  role_id DECIMAL(4) PRIMARY KEY NOT NULL,
  role_code VARCHAR(32),
  role_name VARCHAR(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_sys_user
(
  user_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(32) NOT NULL,
  user_real_name VARCHAR(24),
  user_nick_name VARCHAR(64),
  user_pwd VARCHAR(32) NOT NULL,
  user_pwd_strength CHAR(1),
  email VARCHAR(100),
  mobile VARCHAR(64),
  register_datetime DATETIME,
  register_ip VARCHAR(64),
  last_login_datetime DATETIME,
  last_login_ip VARCHAR(64),
  login_count INT(11),
  user_status CHAR(1),
  error_count INT(11)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_sysuser_role
(
  user_id BIGINT(20) NOT NULL,
  role_code VARCHAR(32),
  role_name VARCHAR(32)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_user
(
  user_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(32),
  user_real_name VARCHAR(24),
  user_nick_name VARCHAR(64),
  user_grade DECIMAL(2),
  user_score INT(11),
  user_pwd VARCHAR(32),
  user_pwd_strength CHAR(1),
  email VARCHAR(100),
  mobile VARCHAR(64) NOT NULL,
  idcard_kind CHAR(1),
  idcard VARCHAR(18),
  user_address VARCHAR(256),
  register_datetime DATETIME,
  register_ip VARCHAR(64),
  last_login_datetime DATETIME,
  last_login_ip VARCHAR(64),
  login_count INT(11),
  user_status CHAR(1),
  error_count INT(11),
  is_trust CHAR(1),
  user_init VARCHAR(2),
  user_kind VARCHAR(8),
  sales_channel VARCHAR(10),
  ref_user_id BIGINT(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX user_idx ON mskl_user (user_kind);
CREATE INDEX user_idx1 ON mskl_user (mobile, register_datetime, is_trust);

CREATE TABLE mskl_user_ext
(
  user_id BIGINT(20) PRIMARY KEY NOT NULL,
  gender CHAR(1),
  birthday VARCHAR(8),
  user_intro VARCHAR(500),
  user_comefrom VARCHAR(64),
  user_qq VARCHAR(20),
  user_phone VARCHAR(50),
  user_photo VARCHAR(256),
  user_signature VARCHAR(256),
  user_occupation VARCHAR(10),
  user_education VARCHAR(10),
  user_disease VARCHAR(64),
  user_age INT(11) DEFAULT '0'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mskl_user_login_log
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20) NOT NULL,
  login_datetime DATETIME NOT NULL,
  login_ip VARCHAR(64),
  is_success CHAR(1)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE mskl_treat_info (
  id bigint(20) NOT NULL auto_increment,
  user_id bigint(20) NOT NULL,
  medicine_id bigint(20) default NULL,
  treat_date date default NULL,
  daily_times int(11) default NULL,
  taken_times int(11) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

