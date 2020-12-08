DO
$$
    BEGIN
        -- Create terminal table
        INSERT INTO quote.terminal_pricing
        ("id", "industry", "price")
        VALUES
        (1, 'Bakeries', 50.00);

        -- Create transaction_count table
        INSERT INTO quote.transaction_count_pricing
        ("id", "industry", "transaction_count", "price")
        VALUES
        (1, 'Bakeries', 100, 10.00),
        (2, 'Bakeries', 5000, 5.00),
        (3, 'Bakeries', 25000, 0.00),
        (4, 'Bakeries', 1000000, 0.00);

        -- Create transaction_volume table
        INSERT INTO quote.transaction_volume_pricing
        ("id", "industry", "transaction_volume", "price")
        VALUES
        (1, 'Bakeries', 5000, 20.00),
        (2, 'Bakeries', 10000, 10.00),
        (3, 'Bakeries', 100000, 0.00),
        (4, 'Bakeries', 1000000, 0.00);

    END
$$;