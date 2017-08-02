DROP TABLE JOURNAL IF EXISTS;
CREATE TABLE JOURNAL(id SERIAL, title VARCHAR(255), summary VARCHAR(255), created TIMESTAMP);

INSERT INTO JOURNAL(title,summary,created) VALUES('스프링 부트 입문3','오늘부터 스프링 부트를 배웠다','2016-01-02 00:00:00.00');
INSERT INTO JOURNAL(title,summary,created) VALUES('간단한 스프링 부트 프로젝트3','스프링 부트 프로젝트를 처음 만들어 보았다','2016-01-03 00:00:00.00');
INSERT INTO JOURNAL(title,summary,created) VALUES('스프링 부트 해부3','스프링 부트를 자세히 살펴보았다','2016-02-02 00:00:00.00');
INSERT INTO JOURNAL(title,summary,created) VALUES('스프링 부트 클라우드3','클라우드 파운드리를 응용한 스프링 부트를 공부했다','2016-02-05 00:00:00.00');