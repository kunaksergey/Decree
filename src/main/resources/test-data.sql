INSERT INTO USERS(FIRST_NAME,LAST_NAME,LOGIN,PASSWORD,BIRTH_DATE)
VALUES ('Sergey','Kunak','sa','kunak999','1980-09-21');
INSERT INTO USERS(FIRST_NAME,LAST_NAME,LOGIN,PASSWORD,BIRTH_DATE)
VALUES ('Lena','Kunak','lena','4444','1980-04-23');
INSERT INTO USERS(FIRST_NAME,LAST_NAME,LOGIN,PASSWORD,BIRTH_DATE)
VALUES ('Kostya','Kunak','kos','1212','2004-06-22');

INSERT INTO message(USER_ID,MESSAGE,MESSAGE_DATE_TIME)
VALUES (1,'bla bla car','2017-06-05T14:15:23');
INSERT INTO message(USER_ID,MESSAGE,MESSAGE_DATE_TIME)
VALUES (1,'RIO RIO RIO','2017-06-06T09:20:20');
INSERT INTO message(USER_ID,MESSAGE,MESSAGE_DATE_TIME)
VALUES (2,'Группа американских ученых предупредила о вероятном
появлении над США гигантской озоновой дыры, о чем отмечено
 на сайте Гарвардского университета. Полный текст исследования был
  обнародован в издании Proceedings of the National Academy of Sciences.','2017-05-06T08:00:12');
INSERT INTO message(USER_ID,MESSAGE,MESSAGE_DATE_TIME)
VALUES (2,'Временный поверенный посольства США в Китае
 Дэвид Ренк подал в отставку на фоне предположений,
  что он выступает против климатической политики президента Дональда Трампа.
   Как сообщает AFP, Ренк – карьерный дипломат, в Госдепе США работает 27 лет.
    На работу в посольство в Пекине он был назначен в январе прошлого года.','2017-06-06T11:20:05');

INSERT INTO role(ROLE_ID)
VALUES ('ROLE_ADMIN');
INSERT INTO role(ROLE_ID)
VALUES ('ROLE_USER');
INSERT INTO role(ROLE_ID)
VALUES ('ROLE_GUEST');

INSERT INTO user_role_detail(USER_ID,ROLE_ID)
VALUES (1,'ROLE_ADMIN');
INSERT INTO user_role_detail(USER_ID,ROLE_ID)
VALUES (1,'ROLE_USER');
INSERT INTO user_role_detail(USER_ID,ROLE_ID)
VALUES (2,'ROLE_USER');
INSERT INTO user_role_detail(USER_ID,ROLE_ID)
VALUES (3,'ROLE_GUEST');







