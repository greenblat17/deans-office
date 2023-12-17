-- liquibase formatted sql

--changeset greenblat17:1
ALTER TABLE "students"
    ADD CONSTRAINT year_range check ( "year" > 1900 AND "year" < 2100 );

ALTER TABLE "students"
    ADD CONSTRAINT group_positive check ( "group" > 0 );

--changeset greenblat17:2
ALTER TABLE "study_plan"
    ADD CONSTRAINT semester_range check ( "semester" > 0 AND "semester" < 20 );

ALTER TABLE "study_plan"
    ADD CONSTRAINT hours_positive check ( "hours" > 0 );

--changeset greenblat17:3
ALTER TABLE "book_grade"
    ADD CONSTRAINT grade_range check ( "grade" >= 2 AND "grade" <= 5)