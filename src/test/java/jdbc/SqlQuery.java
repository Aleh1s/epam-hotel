package jdbc;

public class SqlQuery {
    public static final String SQL_CREATE_ALL_TABLES = """
            CREATE TABLE IF NOT EXISTS public."user"
            (
                id bigint auto_increment,
                login character varying(30) NOT NULL,
                password character varying(20) NOT NULL,
                timezone character varying(40) NOT NULL DEFAULT 'UTC',
                locale character varying(10) NOT NULL DEFAULT 'en',
                role character varying(20) NOT NULL DEFAULT 'CUSTOMER',
                PRIMARY KEY (id),
                CONSTRAINT login_unique UNIQUE (login)
            );
                        
            CREATE TABLE IF NOT EXISTS public.customer
            (
                id bigint auto_increment,
                first_name character varying(50),
                last_name character varying(50),
                date_of_birth date,
                gender character varying(6),
                email character varying(320),
                country character varying(74),
                user_id bigint NOT NULL,
                PRIMARY KEY (id),
                CONSTRAINT customer_email_unique UNIQUE (email),
                CONSTRAINT customer_user_id_unique UNIQUE (user_id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.manager
            (
                id integer,
                first_name character varying(50),
                last_name character varying(50),
                email character varying(320),
                user_id bigint NOT NULL,
                PRIMARY KEY (id),
                CONSTRAINT manager_email_unique UNIQUE (email),
                CONSTRAINT manager_user_id_unique UNIQUE (user_id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.application
            (
                id bigint auto_increment,
                number_of_guests integer,
                apartment_class integer,
                date_of_leaving date,
                date_of_entry date,
                status integer,
                customer_id bigint NOT NULL,
                PRIMARY KEY (id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.room_type
            (
                id bigint auto_increment,
                name character varying(200) NOT NULL,
                attributes character varying(500),
                number_of_beds integer NOT NULL,
                size integer NOT NULL,
                number_of_persons integer NOT NULL,
                PRIMARY KEY (id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.room
            (
                room_number integer,
                price numeric NOT NULL,
                class integer NOT NULL DEFAULT 0,
                status integer NOT NULL DEFAULT 0,
                description text,
                busy_until date,
                room_type_id bigint NOT NULL,
                PRIMARY KEY (room_number)
            );
                        
            CREATE TABLE IF NOT EXISTS public.request
            (
                id bigint auto_increment,
                room_number integer NOT NULL,
                manager_id integer NOT NULL,
                customer_id bigint NOT NULL,
                status integer NOT NULL DEFAULT 0,
                is_reviewed boolean NOT NULL DEFAULT false,
                PRIMARY KEY (id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.request_cansel_reason
            (
                id bigint auto_increment,
                description text NOT NULL,
                request_id bigint NOT NULL,
                PRIMARY KEY (id),
                CONSTRAINT request_id_unique UNIQUE (request_id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.reservation
            (
                id bigint auto_increment,
                room_number integer NOT NULL,
                customer_id bigint NOT NULL,
                date_of_entry date NOT NULL,
                date_of_leaving date NOT NULL,
                created_at timestamp(0) without time zone NOT NULL,
                expiration_date timestamp(0) without time zone NOT NULL,
                bill_id bigint NOT NULL,
                PRIMARY KEY (id),
                CONSTRAINT bill_id_unique UNIQUE (bill_id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.bill
            (
                id bigint auto_increment,
                total_amount numeric NOT NULL,
                is_payed boolean NOT NULL DEFAULT false,
                created_at timestamp(0) without time zone NOT NULL,
                payed_at timestamp(0) without time zone,
                PRIMARY KEY (id)
            );
                        
            CREATE TABLE IF NOT EXISTS public.guest_review
            (
                id bigint auto_increment,
                room_number integer NOT NULL,
                customer_id bigint NOT NULL,
                title character varying(100) NOT NULL,
                advantages character varying(500) NOT NULL,
                disadvantages character varying(500) NOT NULL,
                created_at timestamp(0) without time zone NOT NULL,
                stuff_rating integer NOT NULL,
                comfort_rating integer NOT NULL,
                free_wifi_rating integer NOT NULL,
                facilities_rating integer NOT NULL,
                value_for_money_rating integer NOT NULL,
                cleanliness_rating integer NOT NULL,
                location_rating integer NOT NULL,
                PRIMARY KEY (id)
            );
                        
            ALTER TABLE IF EXISTS public.customer
                ADD CONSTRAINT customer_user_id_fk FOREIGN KEY (user_id)
                    REFERENCES public."user" (id)
                                ON UPDATE NO ACTION
                                   ON DELETE CASCADE;
                        
            ALTER TABLE IF EXISTS public.manager
                ADD CONSTRAINT manager_user_id_fk FOREIGN KEY (user_id)
                    REFERENCES public."user" (id)
                                ON UPDATE NO ACTION
                                   ON DELETE CASCADE;
                        
                        
            ALTER TABLE IF EXISTS public.application
                ADD CONSTRAINT application_customer_id_fk FOREIGN KEY (customer_id)
                    REFERENCES public.customer (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.room
                ADD CONSTRAINT room_type_id_fk FOREIGN KEY (room_type_id)
                    REFERENCES public.room_type (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.request
                ADD CONSTRAINT request_room_number_fk FOREIGN KEY (room_number)
                    REFERENCES public.room (room_number)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.request
                ADD CONSTRAINT manager_id FOREIGN KEY (manager_id)
                    REFERENCES public.manager (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.request
                ADD CONSTRAINT request_customer_id FOREIGN KEY (customer_id)
                    REFERENCES public.customer (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.request_cansel_reason
                ADD CONSTRAINT request_id_fk FOREIGN KEY (request_id)
                    REFERENCES public.request (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.reservation
                ADD CONSTRAINT room_number FOREIGN KEY (room_number)
                    REFERENCES public.room (room_number)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.reservation
                ADD CONSTRAINT reservation_customer_id FOREIGN KEY (customer_id)
                    REFERENCES public.customer (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.reservation
                ADD CONSTRAINT bill_id FOREIGN KEY (bill_id)
                    REFERENCES public.bill (id)
                                ON UPDATE NO ACTION
                                   ON DELETE CASCADE;
                        
                        
            ALTER TABLE IF EXISTS public.guest_review
                ADD CONSTRAINT guest_review_room_number_fk FOREIGN KEY (room_number)
                    REFERENCES public.room (room_number)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
                        
                        
            ALTER TABLE IF EXISTS public.guest_review
                ADD CONSTRAINT guest_review_customer_id_fk FOREIGN KEY (customer_id)
                    REFERENCES public.customer (id)
                                ON UPDATE NO ACTION
                                   ON DELETE NO ACTION;
            """;
}
