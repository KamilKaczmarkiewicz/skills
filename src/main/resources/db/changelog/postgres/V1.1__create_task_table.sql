CREATE TABLE task
(
    id UUID PRIMARY KEY,
    person_id UUID NOT NULL,
    classification VARCHAR(255),

    old_name VARCHAR(255),
    old_surname VARCHAR(255),
    old_company VARCHAR(255),
    old_birth_date DATE,

    new_name VARCHAR(255),
    new_surname VARCHAR(255),
    new_company VARCHAR(255),
    new_birth_date DATE,

    similarity_percentage DOUBLE PRECISION,
    progress_status_percentage DOUBLE PRECISION,

    created_at TIMESTAMP WITH TIME ZONE
);
