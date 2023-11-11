CREATE TABLE `api_keys` (
  `key` varchar(255) PRIMARY KEY NOT NULL,
  `client` varchar(255) NOT NULL
);

CREATE TABLE `logging` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `IP` char(255) COLLATE utf8mb4_general_ci NOT NULL,
  `endpoint` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `request_location` timestamp NOT NULL
);