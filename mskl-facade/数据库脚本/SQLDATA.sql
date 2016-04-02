INSERT INTO mskl.mskl_account (user_id, user_real_name, currency_type, avalaible_amount, freeze_amount, account_status, md5_code, remark, create_datetime, update_datetime, version, hash, account_status_reason) VALUES (999999, '1', '1', 1111, 1, '1', '1', '1', '2016-03-30 23:33:20', '2016-03-30 23:33:31', 1, '1', '1');
INSERT INTO mskl.mskl_feedback (user_id, user_name, user_mobile, feedback_msg, update_datetime) VALUES (999999, 'yangxu', '13545454567', '吃药', '2016-04-01 23:25:19');
INSERT INTO mskl.mskl_medbox (user_id, user_mobile, user_real_name, mskl_medicine_id, medical_name, normal_name, total_amount, taken_amount, remaining_amount, dose, daily_times, start_day, finish_day, update_datetime) VALUES (999999, '18514208469', null, 2, '2', '2', 3, 2, 22, 2, 3, null, null, '2016-04-02 00:32:10');
INSERT INTO mskl.mskl_medicine (medical_name, normal_name, manufacturer, medicine_unit, package_amount, dose, daily_times, med_code, bar_code, update_datetime) VALUES ( '盘尼西林', '青霉素', '007军工厂', '片', 12, 1,3, '1234567', '1234567890', now());
INSERT INTO mskl.mskl_medicine (medical_name, normal_name, manufacturer, medicine_unit, package_amount, dose, daily_times, med_code, bar_code, update_datetime) VALUES ( '禽流感免疫片', 'helloWorld', '008军工厂', '斤', 15, 1,3, '7654321', '0987654321', now());
INSERT INTO mskl.mskl_medicine (medical_name, normal_name, manufacturer, medicine_unit, package_amount, dose, daily_times, med_code, bar_code, update_datetime) VALUES ( '禽流感免疫片1', 'helloWorld1', '009军工厂', '斤', 15, 1,3, '0654321', '9987654321', now());
INSERT INTO mskl.mskl_medicine (medical_name, normal_name, manufacturer, medicine_unit, package_amount, dose, daily_times, med_code, bar_code, update_datetime) VALUES ( 'qinliugan', 'helloWorld13', '009军工厂', '斤', 15, 1,3, '1111111', '11111111', now());
INSERT INTO mskl.mskl_medicine (medical_name, normal_name, manufacturer, medicine_unit, package_amount, dose, daily_times, med_code, bar_code, update_datetime) VALUES ( 'qinliugan1', 'helloWorld4', '009军工厂', '斤', 15, 1,3, '2222222', '22222222', now());
INSERT INTO mskl.mskl_overseer (user_id, user_mobile, overseer, overseer_mobile, update_datetime) VALUES (2, '13512122323', 'yangxu', '13422325533', '2016-04-01 23:32:05');
INSERT INTO mskl.mskl_treat_log (user_id, user_mobile, mskl_medicine_id, medical_name, normal_name, set_alarm, finish_at, taken_status, taken_mood, taken_words, dose, update_datetime) VALUES (999999, '18514208469', 2, '2', '2', '2016-04-01 09:23:00', null, 1, null, null, 2, null);
INSERT INTO mskl.mskl_treat_plan (mskl_medicine_id, medical_name, normal_name, medicine_unit, package_amount, user_id, taken_amount, dose, daily_times, morning_alarm, noon_alarm, night_alarm, update_datetime) VALUES (2, '2', '2', '2', 3, 999999, 2, 2, 3, '2016-04-01 12:23:00', null, null, '2016-04-02 00:32:52');
INSERT INTO mskl.mskl_user_cashout_application (user_id, bank_no, bank_name, amount, bank_addr_no, application_datetime, review_status, pay_datetime) VALUES (999999, '2', '2', 100, '2222', '2016-04-02 00:05:02', null, null);




redis LOGIN999999 "18b04227c42748498c2227e20c66e3b4|999999"