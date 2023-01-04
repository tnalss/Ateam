
-- 사원테이블 생성
CREATE TABLE TBL_EMP (
	EMP_NO	NUMBER		NOT NULL,
	EMP_NAME	VARCHAR2(20)		not NULL,
	GENDER	VARCHAR2(3)	default '남' check (gender in('남','여')),
	PHONE	VARCHAR2(15)		not NULL,
	EMAIL	VARCHAR2(50)		not NULL,
	BIRTH	DATE		NULL,
	ADDRESS	VARCHAR2(300)		NULL,
	EMP_PW	VARCHAR2(300)		not NULL,
	salary	NUMBER(8,2)		NULL,
	HIRE_DATE	DATE	default sysdate,
	END_DATE	 DATE		NULL,
	ADMIN	VARCHAR2(1)		default 'N' check (admin in('Y','N')),
	COMMISSION_PCT	NUMBER		NULL
);

-- 사원번호 시퀀스
create sequence seq_emp_no start with 1 increment by 1 nocache;

-- 사원 번호 트리거
create or replace trigger trg_emp_no 
    before insert on tbl_emp
    for each row
begin
    select seq_emp_no.nextval into :new.emp_no from dual;
end;
/

-- pk
ALTER TABLE TBL_EMP ADD CONSTRAINT PK_TBL_EMP PRIMARY KEY (EMP_NO);

-- 샘플데이터
insert INTO tbl_emp ( emp_name,gender, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('심청' , '여', '010515418847' , 'openeyes@gmail.com' , '95/02/22', '임당수 앞' ,'1234',100000,'',0);

insert INTO tbl_emp ( emp_name, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('박문수' , '01088797884' , 'moosoo@gmail.com' , '89/02/22','여기저기','1234',300000  ,'',0);

insert INTO tbl_emp ( emp_name,gender, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('라푼젤' , '여','01058784424' , 'longhair@gmail.com' , '89/08/21','마법의성','1234',200000  ,'',0);

insert INTO tbl_emp ( emp_name, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('이둘리' , '01052522222' , '2two2@naver.com' , '78/02/11','고길동집','1234',150000  ,'',0);

insert INTO tbl_emp ( emp_name, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('고길동' , '01022221818' , 'hate2two@gmail.com' , '65/08/21','서울 관악구','1234',400000  ,'',0);

insert INTO tbl_emp ( emp_name, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('홍길동' , '01042896464' , 'callfather@gmail.com' , '90/01/11','광주 서구 농성동','1234',400000  ,'',0.5);

insert INTO tbl_emp ( emp_name, phone, email, birth,address,emp_pw,salary,end_date, commission_pct )
VALUES ('고길동' , '01022221818' , 'hate2two@gmail.com' , '65/08/21','서울 관악구','1234',400000  ,'',0);

-- 사원정보 조회 샘플 쿼리
select e.*,c.code_value 직급,c2.code_value 지점명 ,c3.code_value 부서
from tbl_emp e left join tbl_org o
on e.emp_no = o.emp_no
left join tbl_code c
on o.rank_code = c.code_category||c.code_num
left join tbl_code c2
on o.branch_code  = c2.code_category||c2.code_num left join tbl_code c3
on o.dept_code  = c3.code_category||c3.code_num;



-- 연차테이블 등록일 기본값지정
CREATE TABLE TBL_AL (
	AL_NO	NUMBER		NOT NULL,
	EMP_NO	NUMBER		NOT NULL,
	AL_REG_DATE	DATE	default sysdate,
	HOLIDAY_TYPE 	VARCHAR2(30)		NULL,
	AL_USE_DATE	DATE		NULL,
	AL_REASON	VARCHAR2(500)		NULL
);

--시퀀스 연차테이블 al_no
create sequence seq_al_no start with 1 increment by 1 nocache;

--트리거  al_no 자동생성
create or replace trigger trg_al_no 
    before insert on tbl_al
    for each row
begin
    select seq_al_no.nextval into :new.al_no from dual;
end;
/

-- 연차테이블 PK지정
ALTER TABLE TBL_AL ADD CONSTRAINT PK_TBL_AL PRIMARY KEY (
	AL_NO
);

-- 연차테이블 FK 지정

ALTER TABLE TBL_AL ADD CONSTRAINT FK_TBL_AL_TO_EMP FOREIGN KEY (emp_no)
REFERENCES TBL_EMP (emp_no);


--근태 테이블
CREATE TABLE TBL_ATTEND (
	ATTEND_DATE  	DATE		NOT NULL,
	EMP_NO 	    NUMBER		NOT NULL,
	ATTEND_ON 	timestamp(7)		NULL,
	ATTEND_OFF	timestamp(7)		NULL,
	ATT_CODE	NUMBER		NULL
);

-- 근태테이블 pk지정
ALTER TABLE TBL_ATTEND ADD CONSTRAINT PK_ATTEND_DATE PRIMARY KEY (
	attend_date    
);


-- 근태테이블fk지정
ALTER TABLE TBL_ATTEND ADD CONSTRAINT FK_TBL_ATTEND_TO_EMP FOREIGN KEY (emp_no)
REFERENCES TBL_EMP (emp_no);



-- 전자결재 테이블 수신인 사원번호로 변경
CREATE TABLE TBL_EA (
	EA_NUM	NUMBER		NOT NULL,
	EA_RECEIVER	number		NOT NULL,
	EMP_NO	NUMBER		NOT NULL,
	EA_TITLE	VARCHAR2(300)		NOT NULL,
	EA_CONTENT	VARCHAR2(4000)		NOT NULL,
	EA_STATUS	varchar2(10)		NULL,
	EA_DATE	    DATE		default sysdate,
	EA_CDATE	DATE		NULL,
	EA_U_DATE	DATE		NULL,
	EA_A_DATE	DATE		NULL,
	EA_R_DATE	DATE		NULL,
	EA_R_STATUAS 	VARCHAR2(10)	NULL,
	EA_POP	VARCHAR2(10)		NULL,
	EA_READ	VARCHAR2(10)		NULL
);

-- 전자결재테이블 PK지정

ALTER TABLE TBL_EA
ADD CONSTRAINT PK_EA_NUM PRIMARY KEY 
(
  EA_NUM 
, EA_RECEIVER 
)
ENABLE;


-- 전자결재테이블fk지정
ALTER TABLE TBL_EA ADD CONSTRAINT FK_TBL_EA_TO_EMP FOREIGN KEY (emp_no)
REFERENCES TBL_EMP (emp_no);

--전자테이블 시퀀스 지정
create sequence seq_ea_num start with 1 increment by 1 nocache;

--트리거  al_no 자동생성
create or replace trigger trg_ea_num 
    before insert on tbl_ea
    for each row
begin
    select seq_ea_num.nextval into :new.ea_num from dual;
end;
/

--샘플데이터 받는사람번호를 사번으로 해야하나?
Insert INTO TBL_EA ( EA_RECEIVER,EMP_NO, EA_TITLE, EA_CONTENT,EA_STATUS ,EA_CDATE,EA_POP,EA_READ)
Values (2,1,'전자결재테스트','전자결재테스트내용','L0',sysdate,'L1','L1');

-----------------공통테이블 !!!!
--공통테이블 관계 설정을 어떻게해야하나?? 안해도 될듯?

CREATE TABLE TBL_TOP_CODE 
(
  CODE_CATEGORY VARCHAR2(1) NOT NULL 
, CODE_NAME VARCHAR2(20) NOT NULL 
, CREATE_DATE DATE DEFAULT sysdate NOT NULL 
, CREATER VARCHAR2(20) NOT NULL 
, CONSTRAINT TBL_TOP_CODE_PK PRIMARY KEY 
  (
    CODE_CATEGORY 
  )
  ENABLE 
);

COMMENT ON COLUMN TBL_TOP_CODE.CODE_CATEGORY IS 'L=로직,P=문서유형,D=부서,W=근무상태,B=지점,V= 휴가구분,R=직급구분,C=캘린더유형,O=게시판유형,S=급여이력';



--공통상위 테이블 샘플데이터
Insert into  tbl_top_code (code_category,code_name,creater)
values  ('L','로직','admin');

Insert into  tbl_top_code (code_category,code_name,creater)
values  ('P','문서유형','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('D','부서','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('W','근무상태','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('B','지점','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('V','휴가구분','admin');

Insert into  tbl_top_code (code_category,code_name,creater)
values  ('R','직급구분','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('C','캘린더유형','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('O','게시판유형','admin');


Insert into  tbl_top_code (code_category,code_name,creater)
values  ('S','급여이력','admin');

--하위 코드 테이블

CREATE TABLE TBL_CODE 
(
  CODE_CATEGORY VARCHAR2(1) NOT NULL 
, CODE_NUM number NOT NULL 
, CODE_VALUE VARCHAR2(20) 
, CODE_VALUE2 VARCHAR2(20) 
, Create_date Date default sysdate
, creater VARCHAR2(20) not null
, CONSTRAINT TBL_CODE_PK PRIMARY KEY 
  (
    CODE_NUM 
  , CODE_VALUE
  )
  ENABLE 
);

COMMENT ON COLUMN TBL_CODE.CODE_CATEGORY IS '코드분류';

COMMENT ON COLUMN TBL_CODE.CODE_NUM IS '코드숫자값';

COMMENT ON COLUMN TBL_CODE.CODE_VALUE IS '코드값';

COMMENT ON COLUMN TBL_CODE.Create_date IS '만든날짜';

COMMENT ON COLUMN TBL_CODE.creater IS '만든사람';


ALTER TABLE TBL_Code ADD CONSTRAINT FK_TBL_Code_to_top FOREIGN KEY (
	code_category
)
REFERENCES TBL_top_code (
	code_category
);


-- 하위코드 샘플데이터
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('L',0,'false','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('L',1,'true','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('P',1,'근로계약서','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('P',2,'비밀계약서','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('D',1,'경영지원부','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('D',2,'시설관리부','admin');

Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater,code_value2)
values ('D',3,'장비팀','admin','D2');


Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('W',0,'출근','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('W',1,'퇴근','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('B',0,'서울','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('B',1,'광주','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('V',0,'연차','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('V',1,'반차','admin');

Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('R',1,'사장','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('R',2,'이사','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('R',3,'부장','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('R',4,'주임','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('R',5,'사원','admin');

Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('C',0,'부서','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('C',1,'회사전체','admin');

Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('O',0,'공지사항','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('O',1,'익명게시판','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('S',0,'미지급','admin');
Insert into tbl_code (CODE_CATEGORY , CODE_NUM, CODE_VALUE, creater)
values ('S',1,'정상지급','admin');

--일정 테이블
CREATE TABLE TBL_SCHE (
	SCHE_NO	NUMBER		not NULL,
	EMP_NO	NUMBER		NOT NULL,
	SCHE_TYPE	VARCHAR2(10)		NULL,
	SCHE_TITLE	VARCHaR2(30)		NULL,
	SCHE_CONTENT	VARCHAR2(500)		NULL,
	SCHE_RED	 DATE		default sysdate,
	SCHE_START	date		NULL,
	SCHE_END 	date		NULL,
	SCHE_STATUS	VARCHAR2(10)			NULL
);


--일정 테이블 PK 지정

ALTER TABLE TBL_SCHE
ADD CONSTRAINT PK_SCHE_NO PRIMARY KEY 
(  SCHE_NO );


-- 일정 테이블fk지정
ALTER TABLE TBL_SCHE ADD CONSTRAINT FK_TBL_SCHE_TO_EMP FOREIGN KEY (emp_no)
REFERENCES TBL_EMP (emp_no);

--일정 테이블 시퀀스 지정
create sequence seq_SCHE_NO start with 1 increment by 1 nocache;

--트리거  al_no 자동생성
create or replace trigger trg_SCHE_NO 
    before insert on TBL_SCHE
    for each row
begin
    select seq_SCHE_NO.nextval into :new.SCHE_NO from dual;
end;
/

--일정 테이블 샘플데이터
insert into tbl_sche (emp_no,sche_type,sche_title,sche_content,sche_start,sche_end,sche_status) 
values (1,'C1','회사휴무','오늘은 쉬자','22/1/2','22/1/2','L1');


-- 샘플 쿼리
select s.*,e.emp_name , c.code_value 타입 , c2.code_value 상태
from tbl_sche s left outer join tbl_emp e
on s.emp_no = e.emp_no inner join tbl_code c
on s.sche_type = c.code_category||c.code_num
inner join tbl_code c2
on s.sche_status = c2.code_category||c2.code_num;

--조직도 테이블 ,테이블 이름 길어서 조금 줄임,칼럼명 수정
CREATE TABLE TBL_org (
	EMP_NO	NUMBER		NOT NULL,
	rank_code	    VARCHAR2(10)		NULL,
	branch_code	VARCHaR2(10)		NULL,
	dept_code	VARCHAR2(10)		NULL
);

--조직도 테이블 pk
ALTER TABLE TBL_ORG
ADD CONSTRAINT PK_EMP_NO PRIMARY KEY 
(
  EMP_NO 
, BRANCH_CODE 
, DEPT_CODE 
)
ENABLE;


-- 조직도 테이블fk지정
ALTER TABLE TBL_org ADD CONSTRAINT FK_TBL_org_TO_EMP FOREIGN KEY (emp_no)
REFERENCES TBL_EMP (emp_no);

-- 샘플데이터 심청, 사원, 광주지점, 경영지원부
insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(1,'R5','B1','D1');

-- 박문수,  주임 ,광주지점, 경영지원부
insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(2,'R4','B1','D1');

-- 라푼젤,  부장 ,서울지점, 시설관리부 장비팀
insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(3,'R3','B0','D3');

--이둘리 사장 
insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(4,'R1','B0','D2');
--고길동 이사
insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(5,'R2','B1','D1');
--홍길동 사원
insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(6,'R5','B0','D2');



-- 샘플 쿼리

select o.*, e.emp_name, c.code_value 직급,c2.code_value 지점명 ,c3.code_value 부서
from tbl_org o left join tbl_emp e
on o.emp_no = e.emp_no left join tbl_code c
on o.rank_code = c.code_category||c.code_num
left join tbl_code c2
on o.branch_code  = c2.code_category||c2.code_num
left join tbl_code c3
on o.dept_code  = c3.code_category||c3.code_num;

---급여 테이블 쿼리~ 현우

-- 급여 테이블

CREATE TABLE TBL_PAY (
	PAY_DATE	DATE	NOT NULL,
	EMP_NO	NUMBER	NOT NULL,
	PAY_MONTH	NUMBER	NOT NULL,
	PAY_CHANGE	NUMBER	NULL,
    CONSTRAINT PK_TBL_PAY PRIMARY KEY (
        PAY_DATE,
        EMP_NO
    )
);

-- 급여 외래키 설정
ALTER TABLE  TBL_PAY  ADD CONSTRAINT  FK_TBL_EMP_TO_TBL_PAY_1  FOREIGN KEY (
	EMP_NO
)
REFERENCES TBL_EMP (
	EMP_NO
);

-- 테스트용 샘플 데이터 
insert into tbl_pay(emp_no,pay_date, pay_month)
values(1,'2022-12-20',230000);
insert into tbl_pay(emp_no,pay_date, pay_month)
values(1,'2022-12-22',210000);
insert into tbl_pay(emp_no,pay_date, pay_month)
values(2,'2022-12-20',300000);
insert into tbl_pay(emp_no,pay_date, pay_month)
values(1,'2022-12-21',300000);

--급여 테스트 셀렉
select * from tbl_pay;

select e.emp_name, p.pay_date, p.pay_month
from tbl_pay p left join tbl_emp e
on p.emp_no = e.emp_no
where e.emp_name = '심청';



-- 게시판 찬재
-- 게시판 테이블 
drop table tbl_board;
drop table tbl_file;
drop table tbl_reply;

CREATE TABLE TBL_BOARD 
(   BOARD_NO NUMBER NOT NULL, 
    BOARD_CATE varchar2(10) NOT NULL , 
    EMP_NO NUMBER NOT NULL , 
    BOARD_TITLE VARCHAR2(300) not null, 
    BOARD_CONTENT VARCHAR2(4000) not null, 
    BOARD_HITS NUMBER default 1, 
    WRITE_DATE DATE default sysdate
) ;

--게시판 테이블 PK지정
ALTER TABLE TBL_BOARD
ADD CONSTRAINT PK_TBL_BOARD PRIMARY KEY 
( BOARD_NO);


-- 게시판 FK지정
ALTER TABLE tbl_board ADD CONSTRAINT FK_TBL_board_TO_EMP FOREIGN KEY (emp_no)
REFERENCES TBL_EMP (emp_no);

--파일테이블
 CREATE TABLE TBL_FILE
   (FILE_NO NUMBER NOT NULL , 
    BOARD_NO NUMBER NOT NULL , 
    file_path VARCHAR2(500 BYTE) not null, 
    file_name VARCHAR2(300 BYTE) not null);


--파일테이블 pk지정
ALTER TABLE TBL_FILE
ADD CONSTRAINT PK_TBL_FILE PRIMARY KEY 
( FILE_NO );

-- 파일 fk(board와 fk)
ALTER TABLE TBL_file ADD CONSTRAINT FK_TBL_file_TO_board FOREIGN KEY (board_NO)
REFERENCES TBL_BOARD (board_NO);


 -- 게시판 댓글 생성
CREATE TABLE TBL_REPLY (
    REPLY_NO NUMBER NOT NULL ,
    BOARD_NO NUMBER NOT NULL ,
    EMP_NO   NUMBER NOT NULL ,  
    REPLY_CONTENT VARCHAR(500) not null,
    REPLY_CREATE_DATE DATE DEFAULT SYSDATE,
    REPLY_MODIFY_DATE DATE ,
    REPLY_STATUS    varchar(10) default 'L0'
);

-- 게시판 댓글 pk 
ALTER TABLE TBL_REPLY ADD CONSTRAINT PK_TBL_REPLY PRIMARY KEY (REPLY_NO,emp_no);

--댓글 테이블 fk

ALTER TABLE TBL_reply ADD CONSTRAINT FK_TBL_reply_TO_board FOREIGN KEY (board_NO)
REFERENCES TBL_board (board_NO);

ALTER TABLE TBL_reply ADD CONSTRAINT FK_TBL_reply_TO_emp FOREIGN KEY (emp_NO)
REFERENCES TBL_emp (emp_NO);


--게시판 시퀀스 지정
create sequence seq_board_NO start with 1 increment by 1 nocache;

--트리거  al_no 자동생성
create or replace trigger trg_board_NO 
    before insert on TBL_board
    for each row
begin
    select seq_board_NO.nextval into :new.board_NO from dual;
end;
/







////근태테이블 수정 23/1/3 csm
//근태코드가 넘버여서 코드를 받기 위해 문자로 수정하였습니다.
//근태의 attend_date 의 기본값이 없어서 신규 생성하였습니다.

--근태 테이블
CREATE TABLE TBL_ATTEND (
	ATTEND_DATE  	DATE		default sysdate,
	EMP_NO 	    NUMBER		NOT NULL,
	ATTEND_ON 	timestamp(7)		NULL,
	ATTEND_OFF	timestamp(7)		NULL,
	ATT_CODE	VARCHAR2(10)		NULL
);

퇴근할때는 update를 이용해야 데이터가 늘어나지 않습니다.
insert into tbl_attend (emp_no,att_code, attend_on)
values (2,'W0', sysdate);

UPDATE tbl_attend
SET attend_off = sysdate , att_code='W1'
WHERE emp_no=2; 
