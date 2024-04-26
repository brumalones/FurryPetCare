insert into login (
    username,password,email,is_enabled,created_at,role_authorities)
values (
        'administrator', '$2a$10$Mtfi1jv9aM0RoLwWYlWUjOGCp/qeFpcihef/kJLuWP0e8ZNSX8ZIa',
        'brumalones+admin@gmail.com',true,now(),'ROLE_ADMINISTRATOR');

--username: administrator
--password: marmite