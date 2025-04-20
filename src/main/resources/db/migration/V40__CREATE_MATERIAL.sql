-- SQL Schema (create_tables.sql)

CREATE TABLE zoom (
                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      title VARCHAR(255),
                      status VARCHAR(50),
                      planned_date_time TIMESTAMP
);

CREATE TABLE material (
                          id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                          title VARCHAR(255),
                          description TEXT,
                          image_id BIGINT,
                          status VARCHAR(50),
                          zoom_id BIGINT,
                          FOREIGN KEY (image_id) REFERENCES base64images(id),
                          FOREIGN KEY (zoom_id) REFERENCES zoom(id)
);

CREATE TABLE material_tags (
                               material_id BIGINT,
                               tags_id BIGINT,
                               PRIMARY KEY(material_id, tags_id),
                               FOREIGN KEY(material_id) REFERENCES material(id),
                               FOREIGN KEY(tags_id) REFERENCES tags(id)
);
