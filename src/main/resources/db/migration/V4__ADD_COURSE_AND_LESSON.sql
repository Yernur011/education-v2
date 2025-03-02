-- Insert default images
INSERT INTO base64images (base64image)
VALUES ('https://i0.wp.com/e4developer.com/wp-content/uploads/2018/01/spring-boot.png?w=1300&ssl=1'),
       ('https://miro.medium.com/v2/resize:fit:1400/1*gausz1aacOvPJT1MEuBDTA.png'),
       ('https://img.reg.ru/faq/20-08-2020-postgresql.png');


INSERT INTO course (id, title, description, base64images_id, created_by, created_date, last_updated_by,
                    last_updated_date)
VALUES (1, 'Mastering Spring Boot', 'Deep dive into Spring Boot framework with practical examples.', 1, 'system',
        CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
       (2, 'Go for Java Developers', 'Learn Go by leveraging your existing Java and Spring Boot knowledge.', 2,
        'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
       (3, 'PostgreSQL Essentials', 'Master PostgreSQL database management for scalable applications.', 3, 'system',
        CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);


INSERT INTO lesson (lesson_number, course_id, title, video_url, body_text, status, is_completed, created_by,
                    created_date,
                    last_updated_by, last_updated_date)
VALUES (1, 1, 'Introduction to Spring Boot',
        'https://www.youtube.com/watch?v=FyZFK4LBjj0&list=PL0lO_mIqDDFUYDRzvocu5EsFGBqPM7CIw&index=1&pp=iAQB',
        'Welcome to the Spring Boot course!', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),
       (2, 1, 'Understanding REST APIs', NULL,
        '<h1>This lesson covers RESTful API design.</h1>' ||
        '</br><h2>Understanding REST APIs</h2>
   <p>
     <strong>REST (Representational State Transfer) APIs</strong> are a crucial part of modern web development, allowing systems to communicate seamlessly over the HTTP protocol.
     They enable the integration of different applications by providing a standardized approach to accessing and manipulating resources using URLs.
   </p>
   <p>
     REST APIs use standard HTTP methods such as:
   </p>
   <ul>
     <li><code>GET</code> – Retrieve data from the server.</li>
     <li><code>POST</code> – Create new resources.</li>
     <li><code>PUT</code> – Update existing resources.</li>
     <li><code>DELETE</code> – Remove resources.</li>
   </ul>
   <p>
     A key principle of RESTful services is <em>statelessness</em>, meaning each request from a client to a server must contain all the information needed to understand and process it. This design ensures <strong>scalability</strong> and allows the server to handle multiple clients without maintaining user state between requests.
   </p>
   <p>
     Another core concept is the representation of resources. In REST, resources are identified by URLs, and their representations are typically in formats such as <code>JSON</code> or <code>XML</code>. For example, a resource representing a user might be available at <code>/api/users/{id}</code>.
   </p>
   <p>
     <strong>Why choose REST APIs?</strong> RESTful services are widely adopted because they are simple, lightweight, and flexible. They perform efficiently in distributed systems and are language-agnostic, meaning they can be consumed by applications built with any programming language.
   </p>
   <p>
     In this lesson, we will explore practical examples of building RESTful APIs using modern frameworks, handling HTTP status codes like <code>200 OK</code>, <code>201 Created</code>, and <code>404 Not Found</code>, and best practices for designing intuitive and secure REST endpoints.
   </p>
   <p>
     By the end of this lesson, you will understand how to build robust REST APIs, how RESTful design contributes to clean and maintainable code, and how these APIs power many of today’s web and mobile applications.
   </p>'
           , 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),
       (3, 1, 'Working with Databases',
        'https://www.youtube.com/watch?v=oGK2KufvxM0&list=PL0lO_mIqDDFUYDRzvocu5EsFGBqPM7CIw&index=4&pp=iAQB', NULL,
        'ACTIVE', FALSE, 'system', NOW(), 'system', NOW());