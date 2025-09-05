-- Table for recordings
CREATE TABLE IF NOT EXISTS recording (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	image LONGBLOB,
	title VARCHAR(255) NOT NULL,
	year_published INT NOT NULL,
	description TEXT,
	track_count INT NOT NULL,
	link VARCHAR(512)
);

-- Table for performers (ElementCollection of String)
CREATE TABLE IF NOT EXISTS recording_performers (
	recording_id BIGINT NOT NULL,
	performers VARCHAR(255),
	FOREIGN KEY (recording_id) REFERENCES recording(id) ON DELETE CASCADE
);

-- Table for samples (ElementCollection of Embeddable Sample)
CREATE TABLE IF NOT EXISTS recording_samples (
	recording_id BIGINT NOT NULL,
	name VARCHAR(255),
	audio LONGBLOB,
	FOREIGN KEY (recording_id) REFERENCES recording(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS admin (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	password_hash VARCHAR(255) NOT NULL,
	last_password_change DATETIME NOT NULL,
	locked BOOLEAN NOT NULL,
	role VARCHAR(50) NOT NULL
);

-- Table for videos
CREATE TABLE IF NOT EXISTS video (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	thumbnail VARCHAR(255),
	embed_id VARCHAR(255) NOT NULL
);
