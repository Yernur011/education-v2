INSERT INTO public.users (username, password, role, enabled, created_at, updated_at)
VALUES (
           'user@gmail.com',
           '$2a$10$C7vsbXnPJ1K3mIE74YQb2OUoKyMHiMLIDc/76RkQfj0WH8EfvnQWK',
           'user',
           TRUE,
           NOW(),
           NOW()
       );