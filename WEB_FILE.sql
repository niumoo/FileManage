-- Create table
create table WEB_FILE
(
  id           NUMBER(20) not null,
  file_name    VARCHAR2(200) not null,
  content      BLOB not null,
  upload_time  DATE not null,
  download     NUMBER(20) default 0 not null,
  content_type VARCHAR2(100) not null,
  file_size    NUMBER(20) not null
);
-- Add comments to the columns 
comment on column WEB_FILE.id
  is '文件ID';
comment on column WEB_FILE.file_name
  is '文件名称';
comment on column WEB_FILE.content
  is '文件二进制内容';
comment on column WEB_FILE.upload_time
  is '上传时间';
comment on column WEB_FILE.download
  is '下载次数';
comment on column WEB_FILE.content_type
  is '文件类型';
comment on column WEB_FILE.file_size
  is '文件大小';
-- Create/Recreate primary, unique and foreign key constraints 
alter table WEB_FILE
  add constraint WEB_FILE_KEY primary key (ID);
  
  
-- 编写自增序列
-- Create sequence 
create sequence SEQ_FILE_WEB_ID -- 序列名称
minvalue 1 -- 序列可以生成的最小值，降序
maxvalue 999999999999999999 -- 序列可以生成的最大值，升序
start with 1 -- 序列开始时候的序列号
increment by 1 -- 序列每次增长的幅度，默认1
cache 20 -- 缓存大小，允许更快的生成序列
CYCLE; -- 序列达到最大值后是否重新开始生成序列，默认NOCYCLE

-- 编写触发器实现WEB_FILE.ID插入数据时自增
-- Create tigger
CREATE OR REPLACE TRIGGER WEB_FILE_INS_TRG  -- 创建或替换序列 
BEFORE INSERT ON WEB_FILE -- 在WEB_FILE表插入之前
FOR EACH ROW -- 表示每插入一行数据触发一次
BEGIN
SELECT SEQ_FILE_WEB_ID.NEXTVAL INTO :NEW.ID FROM DUAL;
-- 用序列值替换ID
END;
/* 
特殊变量:
   new --为一个引用最新的列值;
   old --为一个引用以前的列值; 
*/

