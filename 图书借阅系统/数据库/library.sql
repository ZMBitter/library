SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookNo` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `bookName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `roomId` int(10) DEFAULT NULL,
  `bookNum` int(10) NOT NULL DEFAULT '0',
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `publishAddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`bookNo`),
  KEY `fk_book_room` (`roomId`),
  CONSTRAINT `fk_book_room` FOREIGN KEY (`roomId`) REFERENCES `bookroom` (`roomId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('A215', '白夜行', '1', '4', '村上圭户', '机械工业出版社');
INSERT INTO `book` VALUES ('TH1001', '操作系统', '2', '3', '王鹏', '电子工业出版社');
INSERT INTO `book` VALUES ('TH10010', '数据库2', '2', '5', 'xyu1', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1002', '数据库基本原理', '2', '6', '摩尔', '华东师范出版社');
INSERT INTO `book` VALUES ('TH1003', '数据库1', '2', '5', 'xyu', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1004', '数据库3', '2', '5', 'xyu2', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1005', '数据库4', '2', '5', 'xyu3', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1006', '数据库5', '2', '5', 'xyu4', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1007', '数据库6', '2', '5', 'xyu5', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1008', '数据库7', '2', '5', 'xyu6', '人民邮电出版社');
INSERT INTO `book` VALUES ('TH1009', '数据库8', '2', '5', 'xyu7', '人民邮电出版社');
INSERT INTO `book` VALUES ('TP1001', '计算机网络', '2', '4', '康奈.斯密斯', '清华大学出版社');
INSERT INTO `book` VALUES ('TP1002', 'MySQL从入门到精通', '2', '9', '玫红', '人民邮电出版社');
INSERT INTO `book` VALUES ('TP1003', 'Java面试宝典', '2', '2', '何昊', '人民邮电出版社');
INSERT INTO `book` VALUES ('TP1004', 'Java面试', '2', '2', '张咪', '人民邮电出版社');

-- ----------------------------
-- Table structure for bookroom
-- ----------------------------
DROP TABLE IF EXISTS `bookroom`;
CREATE TABLE `bookroom` (
  `roomId` int(10) NOT NULL,
  `roomAddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookroom
-- ----------------------------
INSERT INTO `bookroom` VALUES ('1', '语言文学借阅室');
INSERT INTO `bookroom` VALUES ('2', '工程技术借阅室');
INSERT INTO `bookroom` VALUES ('3', '艺术设计借阅室');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `userId` int(20) NOT NULL,
  `bookNo` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `borrowTime` date DEFAULT NULL,
  `returnTime` date DEFAULT NULL,
  `bId` int(11) NOT NULL AUTO_INCREMENT COMMENT '借阅信息',
  PRIMARY KEY (`bId`),
  KEY `fk_user_borrow` (`userId`),
  KEY `fk_book_borrow` (`bookNo`),
  CONSTRAINT `fk_book_borrow` FOREIGN KEY (`bookNo`) REFERENCES `book` (`bookNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_borrow` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `returnbook`;
CREATE TABLE `returnbook` (
  `userId` int(10) NOT NULL,
  `bookNo` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `borrowTime` date DEFAULT NULL,
  `realReturnTime` date DEFAULT NULL,
  `rId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`rId`),
  KEY `fk_user_return` (`userId`),
  KEY `fk_book_return` (`bookNo`),
  CONSTRAINT `fk_book_return` FOREIGN KEY (`bookNo`) REFERENCES `book` (`bookNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_return` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

INSERT INTO `returnbook` VALUES ('1002', 'A215', '2019-12-23', '2019-12-23', '55');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1001', 'jack', '111111', 'jack@163.com');
INSERT INTO `user` VALUES ('1002', 'admin', '123456', 'admin@16.com');
INSERT INTO `user` VALUES ('1003', 'zm1997', '2323456', '15829555231@163.com');
DROP TRIGGER IF EXISTS `add_return`;
DELIMITER ;;
CREATE TRIGGER `add_return` AFTER DELETE ON `borrow` FOR EACH ROW BEGIN
      insert into returnbook(userId,bookNo,borrowTime,realReturnTime) values(old.userId,old.bookNo,old.borrowTime,CURDATE());
   END
;;
DELIMITER ;
