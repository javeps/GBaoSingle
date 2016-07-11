-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-07-11 18:28:18
-- 服务器版本： 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gbosng_log`
--
CREATE DATABASE IF NOT EXISTS `gbosng_log` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gbosng_log`;

DELIMITER $$
--
-- 存储过程
--
DROP PROCEDURE IF EXISTS `pro_rnkDel`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnkDel`()
BEGIN
	DECLARE b INT DEFAULT 0;
	DECLARE deltb VARCHAR(100) CHARACTER SET utf8 DEFAULT '';
    DECLARE cur CURSOR FOR SELECT CONCAT('DROP TABLE IF EXISTS ', table_name, ';' ) AS dtbname FROM information_schema.tables WHERE (table_name LIKE 'ranksword%' OR table_name LIKE 'rankwheel%' OR table_name LIKE 'rankscore%' OR table_name LIKE 'rankstars%') AND (table_name NOT LIKE CONCAT("%",DATE_FORMAT(NOW(),'%Y%m%d'),"%") AND table_name NOT LIKE CONCAT("%",DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 1 DAY),'%Y%m%d'),"%") AND table_name NOT LIKE CONCAT("%",DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 2 DAY),'%Y%m%d'),"%") AND table_name NOT LIKE CONCAT("%",DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 3 DAY),'%Y%m%d'),"%"));
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET b = 1;
    OPEN cur;
	FETCH cur INTO deltb;
	WHILE b <> 1 DO
		SET @sql1 = CONCAT("",deltb);
		PREPARE sql1 FROM @sql1;
		EXECUTE sql1;
		DEALLOCATE PREPARE sql1;
		SET b = 0;
		FETCH cur INTO deltb;
    END WHILE;
    CLOSE cur;
END$$

DELIMITER ;
--
-- Database: `gbosng`
--
CREATE DATABASE IF NOT EXISTS `gbosng` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gbosng`;

DELIMITER $$
--
-- 存储过程
--
DROP PROCEDURE IF EXISTS `pro_rnkScore_Chn`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnkScore_Chn`(IN `chn` VARCHAR(64))
BEGIN
	DECLARE rnk varchar(64) character set utf8;
    SET rnk = concat("gbosng_log.rankscore",DATE_FORMAT(NOW(),'%Y%m%d'));

    IF NOT (chn is null OR chn = '') THEN
    	SET rnk = concat(rnk,"_",chn);
    END IF;

    SET @drop = concat("DROP TABLE IF EXISTS ",rnk);
    PREPARE drop1 FROM @drop;
    EXECUTE drop1;
    
    SET @crt = concat(
    	"CREATE TABLE IF NOT EXISTS ",
    	rnk,
    	" (",
		"`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '排行榜唯一标识',",
		"`indexs` int(11) NOT NULL COMMENT '排序',",
		"`unqid` varchar(128) NOT NULL COMMENT '帐号登录唯一标识',",
		"`pcid` int(11) NOT NULL COMMENT '用户标识',",
		"`pname` varchar(32) NOT NULL COMMENT '角色名字',",
		"`score` int(11) NOT NULL COMMENT '无尽得分',",
		"PRIMARY KEY (`id`),",
		"UNIQUE KEY `unqid` (`unqid`)",
		") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;"
	);
	PREPARE crt1 FROM @crt;
    EXECUTE crt1;
	
	SET @in2 = concat(
    	"INSERT INTO ",
    	rnk,
    	"(`indexs`,`unqid`,`pcid`,`pname`,`score`)"
	);
	
	IF chn is null OR chn = '' THEN
	SET @in2 = concat(
    	@in2,
		"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`score4Endless` FROM `player`,(SELECT @rownum:=0) r ORDER BY `score4Endless` DESC,`lasttime` DESC,`pcid` ASC;"
	);
	ELSE
	SET @in2 = concat(
    	@in2,
		"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`score4Endless` FROM `player`,(SELECT @rownum:=0) r WHERE `player`.`chnSub` = '",chn,"' ORDER BY `score4Endless` DESC,`lasttime` DESC,`pcid` ASC;"
	);
	END IF;
	PREPARE in2 FROM @in2;
    EXECUTE in2;
END$$

DROP PROCEDURE IF EXISTS `pro_rnkStars_Chn`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnkStars_Chn`(IN `chn` VARCHAR(64))
BEGIN
	DECLARE rnk varchar(64) character set utf8;
    SET rnk = concat("gbosng_log.rankstars",DATE_FORMAT(NOW(),'%Y%m%d'));

    IF NOT (chn is null OR chn = '') THEN
    	SET rnk = concat(rnk,"_",chn);
    END IF;

    SET @drop = concat("DROP TABLE IF EXISTS ",rnk);
    PREPARE drop1 FROM @drop;
    EXECUTE drop1;
    
    SET @crt = concat(
    	"CREATE TABLE IF NOT EXISTS ",
    	rnk,
    	" (",
		"`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '排行榜唯一标识',",
		"`indexs` int(11) NOT NULL COMMENT '排序',",
		"`unqid` varchar(128) NOT NULL COMMENT '帐号登录唯一标识',",
		"`pcid` int(11) NOT NULL COMMENT '用户标识',",
		"`pname` varchar(32) NOT NULL COMMENT '角色名字',",
		"`stars` int(11) NOT NULL COMMENT '所得星数得分',",
		"PRIMARY KEY (`id`),",
		"UNIQUE KEY `unqid` (`unqid`)",
		") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;"
	);
	PREPARE crt1 FROM @crt;
    EXECUTE crt1;
	
	SET @in2 = concat(
    	"INSERT INTO ",
    	rnk,
    	"(`indexs`,`unqid`,`pcid`,`pname`,`stars`)"
	);
	
	IF chn is null OR chn = '' THEN
	SET @in2 = concat(
    	@in2,
		"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`npcStars` FROM `player`,(SELECT @rownum:=0) r ORDER BY `npcStars` DESC,`lasttime` DESC,`pcid` ASC;"
	);
	ELSE
	SET @in2 = concat(
    	@in2,
		"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`npcStars` FROM `player`,(SELECT @rownum:=0) r WHERE `player`.`chnSub` = '",chn,"' ORDER BY `npcStars` DESC,`lasttime` DESC,`pcid` ASC;"
	);
	END IF;
	PREPARE in2 FROM @in2;
    EXECUTE in2;
END$$

DROP PROCEDURE IF EXISTS `pro_rnkSword_Chn`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnkSword_Chn`(in parsType int,in chn varchar(64) character set utf8)
BEGIN
	DECLARE rnk varchar(64) character set utf8;
	
	SET rnk = concat("gbosng_log.ranksword",DATE_FORMAT(NOW(),'%Y%m%d'));
	IF parsType = 1 THEN
		SET rnk = concat(rnk,"_hero");
	ELSEIF parsType = 2 THEN
		SET rnk = concat(rnk,"_part");
	END IF;
	
    IF NOT (chn is null OR chn = '') THEN
    	SET rnk = concat(rnk,"_",chn);
    END IF;
    
    SET @drop = concat("DROP TABLE IF EXISTS ",rnk);
    PREPARE drop1 FROM @drop;
    EXECUTE drop1;
    
    SET @crt = concat(
    	"CREATE TABLE IF NOT EXISTS ",
    	rnk,
    	" (",
		"`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '排行榜唯一标识',",
		"`indexs` int(11) NOT NULL COMMENT '排序',",
		"`unqid` varchar(128) NOT NULL COMMENT '帐号登录唯一标识',",
		"`pcid` int(11) NOT NULL COMMENT '用户标识',",
		"`pname` varchar(32) NOT NULL COMMENT '角色名字',",
		"`sword` int(11) NOT NULL COMMENT '战斗力',",
		"PRIMARY KEY (`id`),",
		"UNIQUE KEY `unqid` (`unqid`)",
		") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;"
	);
	PREPARE crt1 FROM @crt;
    EXECUTE crt1;
	
	SET @in2 = concat(
    	"INSERT INTO ",
    	rnk,
    	"(`indexs`,`unqid`,`pcid`,`pname`,`sword`)"
	);
	
	IF chn is null OR chn = '' THEN
		IF parsType = 1 THEN
			SET @in2 = concat(
				@in2,
				"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`fight4hero` FROM `player`,(SELECT @rownum:=0) r ORDER BY `fight4hero` DESC,`lasttime` DESC,`pcid` ASC;"
			);
		ELSEIF parsType = 2 THEN
			SET @in2 = concat(
				@in2,
				"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`fight4part` FROM `player`,(SELECT @rownum:=0) r ORDER BY `fight4part` DESC,`lasttime` DESC,`pcid` ASC;"
			);
		ELSE
			SET @in2 = concat(
				@in2,
				"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`sword` FROM `player`,(SELECT @rownum:=0) r ORDER BY `sword` DESC,`lasttime` DESC,`pcid` ASC;"
			);
		END IF;
    ELSE
		IF parsType = 1 THEN
			SET @in2 = concat(
				@in2,
				"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`fight4hero` FROM `player`,(SELECT @rownum:=0) r WHERE `player`.`chnSub` = '",chn,"' ORDER BY `fight4hero` DESC,`lasttime` DESC,`pcid` ASC;"
			);
		ELSEIF parsType = 2 THEN
			SET @in2 = concat(
				@in2,
				"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`fight4part` FROM `player`,(SELECT @rownum:=0) r WHERE `player`.`chnSub` = '",chn,"' ORDER BY `fight4part` DESC,`lasttime` DESC,`pcid` ASC;"
			);
		ELSE
			SET @in2 = concat(
				@in2,
				"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`sword` FROM `player`,(SELECT @rownum:=0) r WHERE `player`.`chnSub` = '",chn,"' ORDER BY `sword` DESC,`lasttime` DESC,`pcid` ASC;"
			);
		END IF;
    END IF;
    
	PREPARE in2 FROM @in2;
    EXECUTE in2;
END$$

DROP PROCEDURE IF EXISTS `pro_rnkWheel`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnkWheel`()
BEGIN
	DECLARE rnk varchar(64) character set utf8;
    SET rnk = concat("gbosng_log.rankwheel",DATE_FORMAT(NOW(),'%Y%m%d'));
    
    SET @drop = concat("DROP TABLE IF EXISTS ",rnk);
    PREPARE drop1 FROM @drop;
    EXECUTE drop1;
    
    SET @crt = concat(
    	"CREATE TABLE IF NOT EXISTS ",
    	rnk,
    	" (",
		"`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '排行榜唯一标识',",
		"`indexs` int(11) NOT NULL COMMENT '排序',",
		"`unqid` varchar(128) NOT NULL COMMENT '帐号登录唯一标识',",
		"`pcid` int(11) NOT NULL COMMENT '用户标识',",
		"`pname` varchar(32) NOT NULL COMMENT '角色名字',",
		"`wheel` int(11) NOT NULL COMMENT '无尽循环最大次数',",
		"PRIMARY KEY (`id`),",
		"UNIQUE KEY `unqid` (`unqid`)",
		") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;"
	);
	PREPARE crt1 FROM @crt;
    EXECUTE crt1;
	
	SET @in2 = concat(
    	"INSERT INTO ",
    	rnk,
    	"(`indexs`,`unqid`,`pcid`,`pname`,`wheel`)",
		"SELECT @rownum:=@rownum+1 AS `index`,`unqid`,`pcid`,`pname`,`wheel` FROM `player`,(SELECT @rownum:=0) r ORDER BY `wheel` DESC,`lasttime` DESC,`pcid` ASC;"
	);
	PREPARE in2 FROM @in2;
    EXECUTE in2;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `cop4fee`
--

DROP TABLE IF EXISTS `cop4fee`;
CREATE TABLE IF NOT EXISTS `cop4fee` (
`id` int(11) NOT NULL COMMENT '标识',
  `unqkey` varchar(64) NOT NULL COMMENT '操作唯一标识',
  `chn` varchar(128) NOT NULL COMMENT '渠道标识',
  `copfee` int(4) NOT NULL COMMENT '弹窗计费点控制',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `lasttime` datetime NOT NULL COMMENT '最后操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户导向过程控制之弹窗计费点控制';

-- --------------------------------------------------------

--
-- 表的结构 `email`
--

DROP TABLE IF EXISTS `email`;
CREATE TABLE IF NOT EXISTS `email` (
`id` int(11) NOT NULL COMMENT '标识',
  `unqid` varchar(128) NOT NULL COMMENT 'Player唯一标识',
  `title` varchar(256) NOT NULL COMMENT '邮件主题',
  `content` text NOT NULL COMMENT '邮件内容',
  `awardJson` text NOT NULL COMMENT '奖励[{tpGet,tpId,tpVal}] [string] (道具, 时装是道具ID, 主角/小伙伴是GID)',
  `isRead` bit(1) NOT NULL COMMENT '是否已读',
  `isReceive` bit(1) NOT NULL COMMENT '是否已经领取奖励',
  `creattime` bigint(20) NOT NULL COMMENT '获取邮件时间',
  `validtime` bigint(20) NOT NULL COMMENT '邮件过期时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人邮件';

-- --------------------------------------------------------

--
-- 表的结构 `email4rnk`
--

DROP TABLE IF EXISTS `email4rnk`;
CREATE TABLE IF NOT EXISTS `email4rnk` (
`id` int(11) NOT NULL COMMENT '标识',
  `indexBegin` int(11) NOT NULL COMMENT '排名段开始点',
  `indexEnd` int(1) NOT NULL COMMENT '排名段结束点',
  `title` varchar(256) NOT NULL COMMENT '邮件主题',
  `content` text NOT NULL COMMENT '邮件内容',
  `awardJson` text NOT NULL COMMENT '奖励[{tpGet,tpId,tpVal}] [string] (道具, 时装是道具ID, 主角/小伙伴是GID)',
  `creattime` bigint(20) NOT NULL COMMENT '获取邮件时间',
  `validtime` bigint(20) NOT NULL COMMENT '邮件过期时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排名奖励邮件';

-- --------------------------------------------------------

--
-- 表的结构 `logs4rnk`
--

DROP TABLE IF EXISTS `logs4rnk`;
CREATE TABLE IF NOT EXISTS `logs4rnk` (
`id` int(11) NOT NULL COMMENT '标识',
  `topindex` int(11) NOT NULL COMMENT '奖励排名',
  `unqid` varchar(128) NOT NULL COMMENT 'Player唯一标识',
  `title` varchar(256) NOT NULL COMMENT '邮件主题',
  `content` text NOT NULL COMMENT '邮件内容',
  `awardJson` text NOT NULL COMMENT '奖励[{tpGet,tpId,tpVal}] [string] (道具, 时装是道具ID, 主角/小伙伴是GID)',
  `creattime` bigint(20) NOT NULL COMMENT '领取时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
`pcid` int(11) NOT NULL COMMENT '数据库唯一标识',
  `unqid` varchar(128) NOT NULL COMMENT '帐号登录唯一标识',
  `uuidMCode` varchar(128) NOT NULL COMMENT '手机唯一标识',
  `pname` varchar(32) NOT NULL COMMENT '角色名字',
  `sword` int(11) NOT NULL COMMENT '战斗力',
  `wheel` int(11) NOT NULL COMMENT '无尽的最大轮回次数',
  `btPl` blob NOT NULL COMMENT '用户二进制',
  `btHero` blob NOT NULL COMMENT '英雄',
  `btPart` blob NOT NULL COMMENT '伙伴',
  `btProp` blob NOT NULL COMMENT '道具',
  `btNpc` blob NOT NULL COMMENT 'npc',
  `btEmail` blob NOT NULL COMMENT '邮件',
  `phone` varchar(32) NOT NULL COMMENT '手机号码',
  `createtime` datetime NOT NULL DEFAULT '2015-08-10 00:00:00' COMMENT '创建时间',
  `lasttime` datetime NOT NULL DEFAULT '2015-08-10 00:00:00' COMMENT '最后操作时间',
  `statusActivity` int(11) NOT NULL COMMENT '活动状态',
  `score4Endless` int(11) NOT NULL COMMENT '无尽关卡得分',
  `chn` varchar(32) NOT NULL DEFAULT 'none' COMMENT '主渠道',
  `chnSub` varchar(32) NOT NULL DEFAULT 'none' COMMENT '子渠道',
  `fight4hero` int(11) NOT NULL DEFAULT '0' COMMENT '英雄总战斗力',
  `fight4part` int(11) NOT NULL DEFAULT '0' COMMENT '小伙伴总战斗力',
  `npcStars` int(11) NOT NULL COMMENT '所得星数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `rankscore`
--

DROP TABLE IF EXISTS `rankscore`;
CREATE TABLE IF NOT EXISTS `rankscore` (
`id` int(11) NOT NULL COMMENT '标识',
  `indexs` int(11) NOT NULL COMMENT '排名',
  `unqid` varchar(128) NOT NULL COMMENT '登录唯一标识',
  `pcid` int(11) NOT NULL COMMENT '用户标识',
  `pname` varchar(32) NOT NULL COMMENT '用户名',
  `score` int(11) NOT NULL COMMENT '无尽分数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `rankstars`
--

DROP TABLE IF EXISTS `rankstars`;
CREATE TABLE IF NOT EXISTS `rankstars` (
`id` int(11) NOT NULL COMMENT '标识',
  `indexs` int(11) NOT NULL COMMENT '排名',
  `unqid` varchar(128) NOT NULL COMMENT '登录唯一标识',
  `pcid` int(11) NOT NULL COMMENT '用户标识',
  `pname` varchar(32) NOT NULL COMMENT '用户名',
  `stars` int(11) NOT NULL COMMENT '得的等级星数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ranksword`
--

DROP TABLE IF EXISTS `ranksword`;
CREATE TABLE IF NOT EXISTS `ranksword` (
`id` int(11) NOT NULL COMMENT '标识',
  `indexs` int(11) NOT NULL COMMENT '排名',
  `unqid` varchar(128) NOT NULL COMMENT '登录唯一标识',
  `pcid` int(11) NOT NULL COMMENT '用户标识',
  `pname` varchar(32) NOT NULL COMMENT '用户名',
  `sword` int(11) NOT NULL COMMENT '战斗力'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `rankwheel`
--

DROP TABLE IF EXISTS `rankwheel`;
CREATE TABLE IF NOT EXISTS `rankwheel` (
`id` int(11) NOT NULL COMMENT '标识',
  `indexs` int(11) NOT NULL COMMENT '排名',
  `unqid` varchar(128) NOT NULL COMMENT '登录唯一标识',
  `pcid` int(11) NOT NULL COMMENT '用户标识',
  `pname` varchar(32) NOT NULL COMMENT '用户名',
  `wheel` int(11) NOT NULL COMMENT '无尽循环最大次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `recode4error`
--

DROP TABLE IF EXISTS `recode4error`;
CREATE TABLE IF NOT EXISTS `recode4error` (
`id` bigint(20) NOT NULL COMMENT '数据库唯一标识',
  `uuid` varchar(128) NOT NULL COMMENT '手机唯一标识',
  `device` varchar(512) NOT NULL COMMENT '手机型号',
  `error` text NOT NULL COMMENT '错误内容',
  `createtime` datetime NOT NULL DEFAULT '2015-08-10 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cop4fee`
--
ALTER TABLE `cop4fee`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unqkey` (`unqkey`), ADD UNIQUE KEY `chn` (`chn`);

--
-- Indexes for table `email`
--
ALTER TABLE `email`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `email4rnk`
--
ALTER TABLE `email4rnk`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `logs4rnk`
--
ALTER TABLE `logs4rnk`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
 ADD PRIMARY KEY (`pcid`), ADD UNIQUE KEY `unqid` (`unqid`), ADD UNIQUE KEY `pname` (`pname`), ADD KEY `uuidMCode` (`uuidMCode`), ADD KEY `chn` (`chn`), ADD KEY `chnSub` (`chnSub`);

--
-- Indexes for table `rankscore`
--
ALTER TABLE `rankscore`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unqid` (`unqid`);

--
-- Indexes for table `rankstars`
--
ALTER TABLE `rankstars`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unqid` (`unqid`);

--
-- Indexes for table `ranksword`
--
ALTER TABLE `ranksword`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unqid` (`unqid`);

--
-- Indexes for table `rankwheel`
--
ALTER TABLE `rankwheel`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `unqid` (`unqid`);

--
-- Indexes for table `recode4error`
--
ALTER TABLE `recode4error`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cop4fee`
--
ALTER TABLE `cop4fee`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `email`
--
ALTER TABLE `email`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `email4rnk`
--
ALTER TABLE `email4rnk`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `logs4rnk`
--
ALTER TABLE `logs4rnk`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
MODIFY `pcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库唯一标识';
--
-- AUTO_INCREMENT for table `rankscore`
--
ALTER TABLE `rankscore`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `rankstars`
--
ALTER TABLE `rankstars`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `ranksword`
--
ALTER TABLE `ranksword`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `rankwheel`
--
ALTER TABLE `rankwheel`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `recode4error`
--
ALTER TABLE `recode4error`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库唯一标识';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
