INSERT INTO lesson (lesson_number, course_id, title, video_url, body_text, status, is_completed, created_by,
                    created_date,
                    last_updated_by, last_updated_date)
VALUES (4, 2, 'Introduction to Spring Boot',
        'https://www.youtube.com/watch?v=FyZFK4LBjj0&list=PL0lO_mIqDDFUYDRzvocu5EsFGBqPM7CIw&index=1&pp=iAQB',
        'Welcome to the Spring Boot course!', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),
       (5, 2, 'Understanding REST APIs', NULL,
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
       (6, 2, 'Working with Databases',
        'https://www.youtube.com/watch?v=oGK2KufvxM0&list=PL0lO_mIqDDFUYDRzvocu5EsFGBqPM7CIw&index=4&pp=iAQB', NULL,
        'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),

       (7, 3, 'Introduction to Spring Boot',
        'https://www.youtube.com/watch?v=FyZFK4LBjj0&list=PL0lO_mIqDDFUYDRzvocu5EsFGBqPM7CIw&index=1&pp=iAQB',
        'Welcome to the Spring Boot course!', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),
       (8, 3, 'Understanding REST APIs', NULL,
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
       (9, 3, 'Working with Databases',
        'https://www.youtube.com/watch?v=oGK2KufvxM0&list=PL0lO_mIqDDFUYDRzvocu5EsFGBqPM7CIw&index=4&pp=iAQB', NULL,
        'ACTIVE', FALSE, 'system', NOW(), 'system', NOW());


INSERT INTO test (id, title, state, type, created_by, created_date, last_updated_by, last_updated_date)
VALUES (2, 'Java Basics Test', 'ACTIVE', 'MULTIPLE_CHOICE', 'admin', NOW(), 'admin', NOW());
INSERT INTO question (id, question_number, text, test_id)
VALUES (3, 1, 'What is JVM?', 2),
       (4, 2, 'Which keyword is used to inherit a class in Java?', 2);
INSERT INTO answer (id, text, correct, question_id)
VALUES (7, 'Java Virtual Machine', TRUE, 3),
       (8, 'JavaScript Version Manager', FALSE, 3),
       (9, 'Joint Virtual Memory', FALSE, 3);
INSERT INTO answer (id, text, correct, question_id)
VALUES (10, 'extends', TRUE, 4),
       (11, 'implements', FALSE, 4),
       (12, 'inherits', FALSE, 4);


INSERT INTO test (id, title, state, type, created_by, created_date, last_updated_by, last_updated_date)
VALUES (3, 'Java Basics Test', 'ACTIVE', 'MULTIPLE_CHOICE', 'admin', NOW(), 'admin', NOW());
INSERT INTO question (id, question_number, text, test_id)
VALUES (5, 1, 'What is JVM?', 2),
       (6, 2, 'Which keyword is used to inherit a class in Java?', 2);
INSERT INTO answer (id, text, correct, question_id)
VALUES (13, 'Java Virtual Machine', TRUE, 5),
       (14, 'JavaScript Version Manager', FALSE, 5),
       (15, 'Joint Virtual Memory', FALSE, 5);
INSERT INTO answer (id, text, correct, question_id)
VALUES (16, 'extends', TRUE, 6),
       (17, 'implements', FALSE, 6),
       (18, 'inherits', FALSE, 6);

UPDATE public.course SET test_id = 2
where id = 2;

UPDATE public.course SET test_id = 3
where id = 3;