
CREATE TABLE `XXL_CONF_GROUP` (
  `group_name` varchar(100) NOT NULL,
  `group_title` varchar(100) NOT NULL COMMENT '√Ë ˆ',
  PRIMARY KEY (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `XXL_CONF_GROUP` VALUES ('default', 'ƒ¨»œ∑÷◊È');

CREATE TABLE `XXL_CONF_NODE` (
  `node_group` varchar(100) NOT NULL COMMENT '∑÷◊È',
  `node_key` varchar(100) NOT NULL COMMENT '≈‰÷√Key',
  `node_value` varchar(512) DEFAULT NULL COMMENT '≈‰÷√Value',
  `node_desc` varchar(100) DEFAULT NULL COMMENT '≈‰÷√ºÚΩÈ',
  UNIQUE KEY `u_group_key` (`node_group`,`node_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `XXL_CONF_NODE` VALUES ('default', 'key01', '168', '≤‚ ‘≈‰÷√01'), ('default', 'key02', '127.0.0.1:3307', '≤‚ ‘≈‰÷√02');

COMMIT;
