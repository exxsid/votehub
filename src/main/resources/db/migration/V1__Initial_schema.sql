CREATE TABLE users (
    user_id int PRIMARY KEY,
    first_name varchar(255) NOT NULL,
    middle_name varchar(255),
    last_name varchar(255) NOT NULL,
    password varchar NOT NULL,
    salt varchar NOT NULL,
    is_active boolean DEFAULT true,
    roles varchar[] NOT NULL
);
CREATE SEQUENCE users_seq OWNED BY users.user_id START 100001;
ALTER TABLE users ALTER COLUMN user_id SET DEFAULT nextval('users_seq');

CREATE TABLE elections(
    election_id int PRIMARY KEY,
    title varchar NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status varchar(20) DEFAULT 'DRAFT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT valid_election_date CHECK (start_date < end_date)
);
CREATE SEQUENCE elections_seq OWNED BY elections.election_id;
ALTER TABLE elections ALTER COLUMN election_id SET DEFAULT nextval('elections_seq');

CREATE TABLE candidates (
    candidate_id int PRIMARY KEY,
    election_id int REFERENCES elections(election_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    party varchar(100)
);
CREATE SEQUENCE candidates_seq OWNED BY candidates.candidate_id;
ALTER TABLE candidates ALTER COLUMN candidate_id SET DEFAULT nextval('candidates_seq');

CREATE TABLE ballots (
    ballot_id int PRIMARY KEY,
    election_id int REFERENCES elections(election_id),
    user_id int REFERENCES users(user_id),
    cast_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE ballots_seq OWNED BY ballots.ballot_id START 1001;
ALTER TABLE ballots ALTER COLUMN ballot_id SET DEFAULT nextval('ballots_seq');

CREATE TABLE votes (
    vote_id int PRIMARY KEY,
    ballot_id int REFERENCES ballots(ballot_id),
    candidate_id int REFERENCES candidates(candidate_id)
);
CREATE SEQUENCE votes_seq OWNED BY votes.vote_id;
ALTER TABLE votes ALTER COLUMN vote_id SET DEFAULT nextval('votes_seq');


CREATE INDEX idx_election_dates ON elections(start_date, end_date);
CREATE INDEX idx_ballot_election ON ballots(election_id);
CREATE INDEX idx_ballot_user ON ballots(user_id);
CREATE INDEX idx_votes_ballot ON votes(ballot_id);
CREATE INDEX idx_votes_candidate ON votes(candidate_id);