DROP TABLE zcssc.`cp_open_data`;

CREATE TABLE `cp_open_data` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_trunk_number` VARCHAR(3) DEFAULT NULL COMMENT '期号',
  `open_index` ENUM('0','1','2','3','4','5','6','7','8','9') DEFAULT NULL COMMENT '位置',
  `open_number` ENUM('01','02','03','04','05','06','07','08','09','10') DEFAULT NULL COMMENT '号码',
  `open_time` TIMESTAMP NOT NULL COMMENT '开奖时间',
  `insert_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;