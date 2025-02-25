ALTER TABLE public.users
    ADD COLUMN name VARCHAR(255) DEFAULT 'Unknown';

ALTER TABLE public.register_user
    ADD COLUMN name VARCHAR(255) DEFAULT 'Unknown';