CREATE TABLE authority (
    id SMALLSERIAL PRIMARY KEY,
    role VARCHAR(50) UNIQUE NOT NULL
);
INSERT INTO authority (role) VALUES ('ADMIN'), ('USER');

CREATE TABLE gallery_user (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    verified BOOLEAN NOT NULL,
    locked BOOLEAN NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL,
    updated TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE user_authority (
    user_id UUID NOT NULL,
    authority_id SMALLINT NOT NULL,
    PRIMARY KEY (user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES gallery_user(id) ON DELETE CASCADE,
    FOREIGN KEY (authority_id) REFERENCES authority(id) ON DELETE CASCADE
);

CREATE TABLE refresh_token (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    revoked BOOLEAN NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL,
    updated TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES gallery_user(id) ON DELETE CASCADE
);