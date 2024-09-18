--USERS
insert into users (id, email,first_name,last_name, password)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'admin@example.com', 'James','Bond', '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6' ), -- Password: 1234
('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'user@example.com', 'Tyler','Durden', '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6') -- Password: 1234
 ON CONFLICT DO NOTHING;


--ROLES
INSERT INTO role(id, name)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'DEFAULT'),
('ab505c92-7280-49fd-a7de-258e618df074', 'ADMIN'),
('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'USER')
ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authority(id, name)
VALUES ('2ebf301e-6c61-4076-98e3-2a38b31daf86', 'DEFAULT'),
('76d2cbf6-5845-470e-ad5f-2edb9e09a868', 'USER_MODIFY'),
('21c942db-a275-43f8-bdd6-d048c21bf5ab', 'USER_DELETE'),
('001eee79-603b-420b-a51b-dab55b0a05cc', 'USER_CREATE')
ON CONFLICT DO NOTHING;

--assign roles to users
insert into users_role (users_id, role_id)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1'),
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'ab505c92-7280-49fd-a7de-258e618df074'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'c6aee32d-8c35-4481-8b3e-a876a39b0c02')
 ON CONFLICT DO NOTHING;

--assign authorities to roles
INSERT INTO role_authority(role_id, authority_id)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '2ebf301e-6c61-4076-98e3-2a38b31daf86'),
('ab505c92-7280-49fd-a7de-258e618df074', '76d2cbf6-5845-470e-ad5f-2edb9e09a868'),
('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '21c942db-a275-43f8-bdd6-d048c21bf5ab'),
('ab505c92-7280-49fd-a7de-258e618df074', '001eee79-603b-420b-a51b-dab55b0a05cc')
 ON CONFLICT DO NOTHING;

-- Insert a group with a manually specified UUID if auto-generation isn't supported directly in SQL
INSERT INTO groups (id, group_motto, group_name, group_logo_url)
VALUES ('95b11c3e-aaf9-41fc-8dd6-db09585cb8d7', 'Eine für Alle', 'Helfer', 'https://kuppelsteinbad.de/wp-content/uploads/logo.png'),
       ('ca7879c7-0c36-4a56-9a43-11644b980019', 'Noser on Top', 'Noser', 'https://shorturl.at/eba9F');

-- Insert into group_users to associate users with the group
INSERT INTO group_users (group_id, user_id)
VALUES
    ('95b11c3e-aaf9-41fc-8dd6-db09585cb8d7', 'ba804cb9-fa14-42a5-afaf-be488742fc54'), -- User 1
    ('ca7879c7-0c36-4a56-9a43-11644b980019', '0d8fa44c-54fd-4cd0-ace9-2a7da57992de'); -- User 2

