use softwarecapstion;

SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key checking. 
TRUNCATE TABLE club_type; 
truncate table club;
truncate table account;
truncate table user;
truncate table attendance;
truncate table board;
truncate table board_name;
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
  
  INSERT INTO club (id,club_name, club_type, content, file_id) VALUES
  (1,'동아리연합회', 1, null, null),
  (2,'멋쟁이사자처럼', 2, null, null),
  (3,'FLOW', 3, null, null),
  (4,'ELPIS', 4, null, null),
  (5,'아침햇살', 5, null, null);

 INSERT INTO account (id,club_id, price, total, remark, file_id, account_type, date) VALUES
  (1,1, 200000, 200000, null, null, 0, now()),
  (2,1, -200000, 0, null, null, 0, now()),
  (3,1, 100000, 100000, null, null, 1, now()),
  (4,1, -50000, 50000, null, null, 1, now()),
  (5,2, 200000, 200000, null, null, 0, now()),
  (6,2, -200000, 0, null, null, 0, now()),
  (7,3, 200000, 200000, null, null, 0, now()),
  (8,3, -200000, 0, null, null, 0, now()),
  (9,4, 200000, 200000, null, null, 0, now()),
  (10,4, -200000, 0, null, null, 0, now()) ;
  
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
  (10, '장예지', 'JYJ', '1234', '일반회원', null, null);
  
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
  (28, 1, 1, '2020-04-14', 5) ;
  
INSERT INTO board_name (id,board_name) VALUES
  (1, '홍보'),
  (2, '모집'),
  (3, '공지') ;
  
 INSERT INTO board (id, board_name_id, title, content, date, file_id, club_id, start_date, end_date) VALUES
  (1, 1, '아이디어톤 모집', '아이디어톤 홍보 공지', '2020-04-11', null, 2, null, null),
  (2, 1, '해커톤 모집', '해커톤 홍보 공지', '2020-04-12', null, 2, null, null),
  (3, 1, '공연 날짜 안내', '공연 날짜는 5/6부터 5/8까지 입니다.', '2020-04-12', null, 4, null, null) ;
  
 