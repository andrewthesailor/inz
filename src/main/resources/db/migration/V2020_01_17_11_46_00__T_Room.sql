CREATE SEQUENCE S_ROOM_ID START WITH 1 INCREMENT BY 50;

CREATE TABLE T_ROOM(
    ROOM_ID NUMERIC(10,0),
    ROOM_AUDIT_CD DATE NOT NULL DEFAULT NOW(),
    ROOM_AUDIT_MD DATE,
    ROOM_AUDIT_RD DATE,
    ROOM_NAME VARCHAR(100),
    ROOM_SIZE NUMERIC(10,0)
);