use softwarecapstion;

SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key checking. 
TRUNCATE TABLE club_type; 
truncate table club;
truncate table account;
truncate table user;
truncate table attendance;
truncate table board;
truncate table board_name;
truncate table sem_date;
truncate table files;
truncate table user_club;
SET FOREIGN_KEY_CHECKS = 1; -- Enable foreign key checking.

ALTER TABLE `softwarecapstion`.`apply_a` 
DROP FOREIGN KEY `FK_applyA_userId`;
ALTER TABLE `softwarecapstion`.`apply_a` 
ADD CONSTRAINT `FK_applyA_userId`
  FOREIGN KEY (`user_id`)
  REFERENCES `softwarecapstion`.`user` (`id`)
  ON DELETE CASCADE;

ALTER TABLE `softwarecapstion`.`attendance` 
DROP FOREIGN KEY `FK_attendance_userId`;
ALTER TABLE `softwarecapstion`.`attendance` 
ADD CONSTRAINT `FK_attendance_userId`
  FOREIGN KEY (`user_id`)
  REFERENCES `softwarecapstion`.`user` (`id`)
  ON DELETE CASCADE;


INSERT INTO club_type (id,type_name) VALUES
  (1,'동아리연합회'),
  (2,'종교/학술'),
  (3,'문화기획체육'),
  (4,'공연1'),
  (5,'공연2') ; 
  
INSERT INTO club (id,club_name, club_type, content) VALUES
  (1,'동아리연합회', 1, null),
  (2,'멋쟁이사자처럼', 2, null),
  (3,'FLOW', 3, null),
  (4,'ELPIS', 4, null),
  (5,'아침햇살', 5, null),
  (6,'All for RYU', 2, null),
  (7,'행운', 5, null);

 -- INSERT INTO account (id,club_id, price, total, remark, account_type, date) VALUES
 --  (1,1, 200000, 200000, null,  0, '2020-03-02'),
 --  (2,1, -200000, 0, null,  0, '2020-03-03'),
 --  (3,1, 100000, 100000, null, 1, '2020-03-07'),
 --  (4,1, -50000, 50000, null, 1, '2020-03-10'),
 --  (5,2, 200000, 200000, null, 0, '2020-03-02'),
 --  (6,2, -200000, 0, null, 0, '2020-03-02'),
 --  (7,3, 200000, 200000, null, 0, '2020-03-02'),
 --  (8,3, -200000, 0, null, 0, '2020-03-10'),
 --  (9,4, 200000, 200000, null, 0, '2020-03-02'),
 --  (10,4, -200000, 0, null, 0, '2020-03-02'),
 --  (11,1, 200000, 250000, null, 0, '2020-10-10') ;
  
 INSERT INTO user (id, name, login_id, password, user_type, phone, email) VALUES
  (1, '동아리연합회', 'clubAs', '1234', '동연', null, null),
  (2, '멋쟁이사자처럼', 'Lion', '1234', '동아리관리자', null, null),
  (3, 'FLOW', 'Flow', '1234', '동아리관리자', null, null),
  (4, 'ELPIS', 'Elpis', '1234', '동아리관리자', null, null),
  (5, '아침햇살', 'Rice', '1234', '동아리관리자', null, null),
  (6, '탁희윤', 'THY', '1234', '일반회원', null, null),
  (7, '김명석', 'KMS', '1234', '일반회원', null, null),
  (8, '이혜민', 'LHM', '1234', '일반회원', null, null),
  (9, '안세연', 'ASY', '1234', '일반회원', null, null),
  (10, '장예지', 'JYJ', '1234', '일반회원', null, null),
  (11, 'All for RYU', 'RJY', '1234', '동아리관리자', null, null),
  (12, '행운', 'JSW', '1234', '동아리관리자', null, null);
  
LOCK TABLES `user_club` WRITE;
/*!40000 ALTER TABLE `user_club` DISABLE KEYS */;
INSERT INTO `user_club` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,4),(7,6,3),(8,7,2),(9,8,2),(10,8,6),(11,9,5),(12,10,7);
/*!40000 ALTER TABLE `user_club` ENABLE KEYS */;
UNLOCK TABLES;

 INSERT INTO attendance (id, club_id, `check`, date, user_id) VALUES
  (1, 1, 0, '2020-03-03', 2),
  (2, 1, 1, '2020-03-03', 3),
  (3, 1, 1, '2020-03-03', 4),
  (4, 1, 1, '2020-03-03', 5),
  (5, 1, 1, '2020-03-10', 2),
  (6, 1, 1, '2020-03-10', 3),
  (7, 1, 1, '2020-03-10', 4),
  (8, 1, 1, '2020-03-10', 5),
  (9, 1, 1, '2020-03-17', 2),
  (10, 1, 0, '2020-03-17', 3),
  (11, 1, 1, '2020-03-17', 4),
  (12, 1, 0, '2020-03-17', 5),
  (13, 1, 1, '2020-03-24', 2),
  (14, 1, 1, '2020-03-24', 3),
  (15, 1, 0, '2020-03-24', 4),
  (16, 1, 1, '2020-03-24', 5),
  (17, 1, 1, '2020-03-31', 2),
  (18, 1, 1, '2020-03-31', 3),
  (19, 1, 1, '2020-03-31', 4),
  (20, 1, 1, '2020-03-31', 5),
  (21, 1, 1, '2020-04-07', 2),
  (22, 1, 1, '2020-04-07', 3),
  (23, 1, 0, '2020-04-07', 4),
  (24, 1, 1, '2020-04-07', 5),
  (25, 1, 1, '2020-04-14', 2),
  (26, 1, 1, '2020-04-14', 3),
  (27, 1, 1, '2020-04-14', 4),
  (28, 1, 1, '2020-04-14', 5),
  (29, 1, 0, '2020-09-01', 2),
  (30, 1, 1, '2020-09-01', 3),
  (31, 1, 0, '2020-09-01', 4),
  (32, 1, 1, '2020-09-01', 5),
  (33, 1, 1, '2020-09-01', 11),
  (34, 1, 0, '2020-09-01', 12),
  (35, 1, 1, '2020-09-08', 2),
  (36, 1, 1, '2020-09-08', 3),
  (37, 1, 1, '2020-09-08', 4),
  (38, 1, 0, '2020-09-08', 5),
  (39, 1, 1, '2020-09-08', 11),
  (40, 1, 0, '2020-09-08', 12),
  (41, 1, 1, '2020-09-15', 2),
  (42, 1, 0, '2020-09-15', 3),
  (43, 1, 1, '2020-09-15', 4),
  (44, 1, 1, '2020-09-15', 5),
  (45, 1, 0, '2020-09-15', 11),
  (46, 1, 0, '2020-09-15', 12);
  
INSERT INTO board_name (id,board_name) VALUES
  (1, '홍보'),
  (2, '모집'),
  (3, '공지'),
  (4, '회의록');
  
 INSERT INTO board (id, board_name_id, title, content, date, club_id, start_date, end_date) VALUES
  (1, 1, '아이디어톤 모집', '아이디어톤 홍보 공지', '2020-04-11', 2, null, null),
  (2, 1, '해커톤 모집', '해커톤 홍보 공지', '2020-04-12', 2, null, null),
  (3, 1, '공연 날짜 안내', '공연 날짜는 5/6부터 5/8까지 입니다.', '2020-04-12', 4, null, null),
  (4, 2, '멋사 20기 모집', '20기 모집합니다.', '2020-04-10', 2, '2020-04-11', '2020-04-20'),
  (5, 2, '아침햇살 모집', '아침햇살 모집합니다.', '2020-04-10', 5, '2020-04-12', '2020-04-21'),
  (6, 1, '20년 1학기 농구대회', '플로우가 주최하는 농구대회', '2020-04-13', 3, null, null),
  (7, 2, '플로우 모집', '20년 1학기 플로우 모집합니당', '2020-04-13', 3, '2020-04-18', '2020-04-26'),
  (8, 2, 'ELPIS 모집', '20년 1학기 ELPIS 모집!', '2020-04-13', 4, '2020-04-27', '2020-04-30'),
  (9, 3, '공지사항1', '동연 공지사항입니다.', '2020-04-14', 1, null, null),
  (10, 4, '회의록1', '동연 회의록입니다.', '2020-04-15', 1, null, null);
  
  INSERT INTO sem_date (id,start_date, end_date, sem_name) VALUES
  (1, '2020-03-01', '2020-08-31', '2020-1학기'),
  (2, '2020-09-01', '2021-02-28', '2020-2학기');