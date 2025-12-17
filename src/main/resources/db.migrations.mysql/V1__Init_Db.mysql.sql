CREATE TABLE booking
(
    id             INT AUTO_INCREMENT NOT NULL,
    created_at     datetime           NOT NULL,
    updated_at     datetime           NOT NULL,
    booking_status Enum('SCHEDULED','CANCELLED','CAB_ARRIVED','ASSIGNING_DRIVER','IN_RIDE','COMPLETED')       NULL,
    start_time     datetime           NULL,
    end_time       datetime           NULL,
    total_distance BIGINT             NULL,
    review_id      INT                NULL,
    driver_id      INT                NOT NULL,
    passenger_id   INT                NOT NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE driver
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at datetime           NOT NULL,
    updated_at datetime           NOT NULL,
    name       VARCHAR(255)       NULL,
    license_no VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

CREATE TABLE passenger
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at datetime           NOT NULL,
    updated_at datetime           NOT NULL,
    name       VARCHAR(255)       NOT NULL,
    email      VARCHAR(255)       NOT NULL,
    password   VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

CREATE TABLE passenger_review
(
    id                       INT          NOT NULL,
    passenger_review_content VARCHAR(255) NOT NULL,
    passenger_rating         VARCHAR(255) NOT NULL,
    CONSTRAINT pk_passengerreview PRIMARY KEY (id)
);

CREATE TABLE review
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at datetime           NOT NULL,
    updated_at datetime           NOT NULL,
    booking_id INT                NOT NULL,
    content    VARCHAR(255)       NOT NULL,
    rating     DOUBLE             NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

ALTER TABLE driver
    ADD CONSTRAINT uc_driver_licenseno UNIQUE (license_no);

ALTER TABLE review
    ADD CONSTRAINT uc_review_booking UNIQUE (booking_id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);

ALTER TABLE passenger_review
    ADD CONSTRAINT FK_PASSENGERREVIEW_ON_ID FOREIGN KEY (id) REFERENCES review (id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES booking (id);