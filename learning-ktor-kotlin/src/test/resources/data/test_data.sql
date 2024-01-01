INSERT INTO ROOM (NAME, ROOM_NUMBER, BED_INFO) VALUES ('Location 1', 'R1', 'QS');
INSERT INTO ROOM (NAME, ROOM_NUMBER, BED_INFO) VALUES ('Location 1', 'R2', 'KS');
INSERT INTO ROOM (NAME, ROOM_NUMBER, BED_INFO) VALUES ('Location 2', 'R3', 'S');
INSERT INTO ROOM (NAME, ROOM_NUMBER, BED_INFO) VALUES ('Location 2', 'R4', 'D');



INSERT INTO GUEST (LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, COUNTRY, ADDRESS, STATE, PHONE_NUMBER) VALUES('Adams', 'Roy', 'radams1v@xinhuanet.com', 'United States', '2872 Marquette Street', 'NY', '1-(235)314-9823');
INSERT INTO GUEST (LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, COUNTRY, ADDRESS, STATE, PHONE_NUMBER) VALUES('Adams', 'Martin', 'madams2b@msu.edu', 'China', '4 Mandrake Plaza', '', '9-(401)660-9813');
INSERT INTO GUEST (LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, COUNTRY, ADDRESS, STATE, PHONE_NUMBER) VALUES('Alvarez', 'Roger', 'ralvarezk@blogs.com', 'United States', '3 Green Plaza', 'FL', '6-(980)036-6105');
INSERT INTO GUEST (LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, COUNTRY, ADDRESS, STATE, PHONE_NUMBER) VALUES('Alvarez', 'Anne', 'aalvarez1y@mlb.com', 'United States', '6 Glendale Parkway', 'FL', '7-(967)349-7237');
INSERT INTO GUEST (LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, COUNTRY, ADDRESS, STATE, PHONE_NUMBER) VALUES('Anderson', 'Betty', 'banderson14@digg.com', 'United States', '3538 Scofield Drive', 'TX', '1-(291)830-0405');
INSERT INTO GUEST (LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, COUNTRY, ADDRESS, STATE, PHONE_NUMBER) VALUES('Armstrong', 'Christopher', 'carmstrong2p@cyberchimps.com', 'United States', '4514 Independence Point', 'TX', '3-(411)160-3757');

/*
INSERT INTO RESERVATION (ROOM_ID, GUEST_ID, RES_DATE) VALUES ((SELECT ROOM_ID FROM ROOM WHERE ROOM_NUMBER = '22'), (SELECT GUEST_ID FROM GUEST WHERE LAST_NAME = 'Armstrong'), '2022-01-01');
*/
