CREATE SCHEMA IF NOT EXISTS quote;

DO
$$
    BEGIN
        -- Create terminal table
        CREATE TABLE IF NOT EXISTS quote.terminal_pricing
        (
            id BIGSERIAL PRIMARY KEY,
            industry character varying,
            price numeric
        );

        IF NOT EXISTS (SELECT constraint_name
						   FROM information_schema.constraint_column_usage
					   WHERE table_name = 'terminal_pricing'
				   AND constraint_name = 'terminal_pricing_unique')
        THEN
            ALTER TABLE quote.terminal_pricing
                ADD CONSTRAINT terminal_pricing_unique UNIQUE (industry, price);
        END IF;

        -- Create transaction_count table
        CREATE TABLE IF NOT EXISTS quote.transaction_count_pricing
        (
            id BIGSERIAL PRIMARY KEY,
            industry character varying,
            transaction_count integer,
            price numeric
        );

        IF NOT EXISTS (SELECT constraint_name
						   FROM information_schema.constraint_column_usage
					   WHERE table_name = 'transaction_count_pricing'
				   AND constraint_name = 'transaction_count_pricing_unique')
        THEN
            ALTER TABLE quote.transaction_count_pricing
                ADD CONSTRAINT transaction_count_pricing_unique UNIQUE (industry, transaction_count);
        END IF;

        -- Create transaction_volume table
        CREATE TABLE IF NOT EXISTS quote.transaction_volume_pricing
        (
            id BIGSERIAL PRIMARY KEY,
            industry character varying,
            transaction_volume integer,
            price numeric
        );

        IF NOT EXISTS (SELECT constraint_name
						   FROM information_schema.constraint_column_usage
					   WHERE table_name = 'transaction_volume_pricing'
				   AND constraint_name = 'transaction_volume_pricing_unique')
        THEN
            ALTER TABLE quote.transaction_volume_pricing
                ADD CONSTRAINT transaction_volume_pricing_unique UNIQUE (industry, transaction_volume);
        END IF;

        -- @todo - Add access indices
    END
$$;