delete from pack_member;
delete from pack;
INSERT INTO pack VALUES (1,'Rebel Dogs 21','choinoski','littlefish2');
INSERT INTO pack VALUES (2,'Awesome Pack!','awesomepack21','RedBike21');
INSERT INTO pack VALUES (3,'Cool Dawgs','nextdoorpack01','BadgersWin!');
INSERT INTO pack VALUES (4,'Number 4','anotherPack','password');
INSERT INTO pack_member VALUES (1,1,'Odo', 'S','Mixed','M','2016-07-14',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO pack_member VALUES (2,2,'Boomer', 'L','Black Lab','M','2013-08-11',0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO pack_member VALUES (3,2,'Pow', 'L','Black Lab','M','2014-03-22',0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO pack_member VALUES (4,3,'Lexi', 'S','Beagle','F','2011-12-18',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO pack_member VALUES (5,3,'Lucy', 'M','Poodle','F','2016-01-03',0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO pack_member VALUES (6,4,'Dog', 'M','Boxer','M','2007-01-01',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());