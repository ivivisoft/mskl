#更新表结构语句
## 在mskl_treat_log表增加treat_plan_id字段
ALTER TABLE mskl.mskl_treat_log ADD treat_plan_id BIGINT NULL;

ALTER TABLE `mskl`.`mskl_treat_plan`
  CHANGE COLUMN `morning_alarm` `morning_alarm` VARCHAR(16) NULL DEFAULT NULL ,
  CHANGE COLUMN `noon_alarm` `noon_alarm` VARCHAR(16) NULL DEFAULT NULL ,
  CHANGE COLUMN `night_alarm` `night_alarm` VARCHAR(16) NULL DEFAULT NULL ;


ALTER TABLE `mskl`.`mskl_treat_log`
  ADD COLUMN `treat_plan_id` BIGINT(20) NULL;

ALTER TABLE `mskl`.`mskl_treat_log`
  ADD COLUMN `medicine_unit` VARCHAR(8) NULL ;


ALTER TABLE `mskl`.`mskl_push_msg`
  DROP COLUMN `treat_log_id`,
  DROP INDEX `mskl_push_msg_treat_log_id_uindex` ;