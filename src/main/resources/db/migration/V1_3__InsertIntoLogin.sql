insert into login (
    id,username,password,email,is_enabled,created_at,role_authorities)
values (
           '68ae3f54-84c0-439d-8a72-d6d54d472b04','administrator', '$2a$10$Mtfi1jv9aM0RoLwWYlWUjOGCp/qeFpcihef/kJLuWP0e8ZNSX8ZIa',
           'brumalones+admin@gmail.com',true,now(),'ROLE_ADMINISTRATOR');

--username: administrator
--password: marmite