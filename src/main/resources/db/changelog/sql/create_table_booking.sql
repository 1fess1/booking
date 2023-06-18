CREATE TABLE booking (
        id                          BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
        customer_name               VARCHAR NOT NULL,
        table_size                  VARCHAR,
        date                        DATE,
        "from"                      TIME,
        "to"                        TIME,
        active                      BOOLEAN DEFAULT TRUE
)