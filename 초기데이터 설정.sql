use softwarecapstion;

SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key checking. 
TRUNCATE TABLE club_type; 
truncate table club;
truncate table account;
SET FOREIGN_KEY_CHECKS = 1; -- Enable foreign key checking.


INSERT INTO club_type (id,type_name) VALUES
  (1,'종교/학술'),
  (2,'문화기획체육'),
  (3,'공연1'),
  (4,'공연2') ;
  
  INSERT INTO club (id,club_name, club_type, content, file_id) VALUES
  (1,'멋쟁이사자처럼', 1, null, null),
  (2,'FLOW', 2, null, null),
  (3,'ELPIS', 3, null, null),
  (4,'아침햇살', 4, null, null);

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