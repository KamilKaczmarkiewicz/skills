CREATE TABLE task
(
    id UUID PRIMARY KEY,
    person_id UUID NOT NULL,
    progress_status_percentage DOUBLE PRECISION,
    created_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE comparable_object (
    id UUID PRIMARY KEY,
    task_id UUID,
    field_name VARCHAR(255),
    previous_value VARCHAR(255),
    new_value VARCHAR(255),
    classification VARCHAR(255),
    similarity_percentage DOUBLE PRECISION,
    CONSTRAINT fk_task FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE
);
