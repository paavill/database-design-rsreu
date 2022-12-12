CREATE PROCEDURE insert_user(IN username varchar)
    LANGUAGE plpgsql
    AS $$
    BEGIN
        INSERT INTO system_user VALUES(default, 1, 2, username);
    END;
$$;
CREATE PROCEDURE count_users(INOUT users_count int)
    LANGUAGE plpgsql
    AS $$
    BEGIN
        SELECT count(*) INTO users_count FROM system_user;
    END;
$$;
CREATE FUNCTION count_user_with_online_status()
    RETURNS int
    LANGUAGE plpgsql
    AS $$
    DECLARE
        count_result int;
    BEGIN
        SELECT count(*) INTO count_result FROM system_user WHERE status = 1;
        RETURN count_result;
    END;
    $$;