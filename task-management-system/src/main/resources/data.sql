--Default Users for Demo, We can create New Users as well refer Readme
INSERT INTO USERS (username, password, role)
VALUES ('user', '$2a$10$UJ8A7S0PpUizEuuZSKyHw./3b9iRicY91TtuxPqiaA0DpVRkvWqoy', 'USER');
INSERT INTO USERS (username, password, role)
VALUES ('admin', '$2a$10$JgJKzvDiEDQOtgozNF2sWO8JSqN0Fkd93gk0JXBdWAaOyFuuJ74Si', 'ADMIN');

INSERT INTO TASK (user_id, title, description, status, priority, due_date) VALUES (1,'Check Mails', 'Need to Action', 'TODO', 'HIGH', '2024-08-10');
INSERT INTO TASK (user_id, title, description, status, priority, due_date) VALUES (2,'Code Review', 'Need to Action', 'DONE', 'HIGH', '2024-08-10');
INSERT INTO TASK (user_id, title, description, status, priority, due_date) VALUES (2,'Stand UP', 'Need to Action', 'IN_PROGRESS', 'LOW', '2024-07-30');
INSERT INTO TASK (user_id, title, description, status, priority, due_date) VALUES (2,'Check Mails', 'Need to Action', 'IN_PROGRESS', 'LOW', '2024-07-30');
INSERT INTO TASK (user_id, title, description, status, priority, due_date) VALUES (2,'Code Review', 'Need to Action', 'DONE', 'LOW', '2024-08-10');
INSERT INTO TASK (user_id, title, description, status, priority, due_date) VALUES (1,'Stand UP', 'Need to Action', 'TODO', 'MEDIUM', '2024-08-10');

-- Insert data into APP_USERS table (make sure this exists before inserting into USER_DETAILS)
INSERT INTO USER_DETAILS (email, user_id) VALUES ('admin@example.com', 1);
INSERT INTO USER_DETAILS (email, user_id) VALUES ('user@example.com', 2);
