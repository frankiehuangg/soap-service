CREATE TABLE `api_keys` (
  `key` varchar(255) PRIMARY KEY NOT NULL,
  `client` varchar(255) NOT NULL
);

CREATE TABLE `follows` (
    `following_user_id` INT NOT NULL,
    `followed_user_id` INT NOT NULL,
    PRIMARY KEY (`following_user_id`, `followed_user_id`)
);

CREATE TABLE `blocks` (
    `blocking_user_id` INT NOT NULL,
    `blocked_user_id` INT NOT NULL,
    PRIMARY KEY (`blocking_user_id`, `blocked_user_id`)
);

CREATE TABLE `notifications` (
    `notification_id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `notification_content` TEXT NOT NULL,
    `status` VARCHAR(16) NOT NULL
);

CREATE TABLE `logging` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `IP` char(255) COLLATE utf8mb4_general_ci NOT NULL,
  `endpoint` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `request_location` timestamp NOT NULL
);

INSERT INTO `api_keys` VALUES 
('1d2571f6-554a-4ac3-ac1d-43aefeda5668', 'rest client'),
('d1e5fb53-033c-4360-8e74-57f506d283d4', 'monolithic client');