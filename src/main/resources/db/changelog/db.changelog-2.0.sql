-- liquibase formatted sql

--changeset greenblat17:1
CREATE UNIQUE INDEX ON "subject" ("name", "department");

--changeset greenblat17:2
ALTER TABLE "study_plan"
    ADD FOREIGN KEY ("subject_id") REFERENCES "subject" ("id");

--changeset greenblat17:3
ALTER TABLE "academic_performance"
    ADD FOREIGN KEY ("student_id") REFERENCES "students" ("id");

--changeset greenblat17:4
ALTER TABLE "academic_performance"
    ADD FOREIGN KEY ("subject_id") REFERENCES "subject" ("id");
