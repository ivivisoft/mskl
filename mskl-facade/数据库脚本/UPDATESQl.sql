#更新表结构语句
## 在mskl_treat_log表增加treat_plan_id字段
ALTER TABLE mskl.mskl_treat_log ADD treat_plan_id BIGINT NULL;

ALTER TABLE `mskl`.`mskl_treat_plan`
  CHANGE COLUMN `morning_alarm` `morning_alarm` VARCHAR(16) NULL DEFAULT NULL ,
  CHANGE COLUMN `noon_alarm` `noon_alarm` VARCHAR(16) NULL DEFAULT NULL ,
  CHANGE COLUMN `night_alarm` `night_alarm` VARCHAR(16) NULL DEFAULT NULL ;