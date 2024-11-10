CREATE TABLE IF NOT EXISTS member (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- AUTO_INCREMENT 설정
                                      name VARCHAR(100),
    email VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

-- id 값을 명시하지 않고, 자동으로 생성되게 수정
INSERT INTO member (name, email, created_at, updated_at)
VALUES ('martin', 'martin@naver.com', NOW(), NOW());

INSERT INTO member (name, email, created_at, updated_at)
VALUES ('denis', 'denis@naver.com', NOW(), NOW());

INSERT INTO member (name, email, created_at, updated_at)
VALUES ('park', 'park@gmail.com', NOW(), NOW());

INSERT INTO member (name, email, created_at, updated_at)
VALUES ('james', 'jan@naver.com', NOW(), NOW());

INSERT INTO member (name, email, created_at, updated_at)
VALUES ('martin', 'martin@another.com', NOW(), NOW());