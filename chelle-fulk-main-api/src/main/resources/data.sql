

-- Video seeds (insert only if not exists)
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Two Fiddle Tunes', 'https://img.youtube.com/vi/fOxnJQsSRaA/hqdefault.jpg', 'fOxnJQsSRaA' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = 'fOxnJQsSRaA');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Im a Believer', 'https://img.youtube.com/vi/XMJJsnUIxQM/hqdefault.jpg', 'XMJJsnUIxQM' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = 'XMJJsnUIxQM');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Anthem Electric Violin and Cello', 'https://img.youtube.com/vi/C-OkCsUhpE4/hqdefault.jpg', 'C-OkCsUhpE4' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = 'C-OkCsUhpE4');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Tom Teasley Percussion, Chelle Fulk Violin', 'https://img.youtube.com/vi/609TvV1iT0A/hqdefault.jpg', '609TvV1iT0A' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = '609TvV1iT0A');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'The Kiss from Last of the Mohicans', 'https://img.youtube.com/vi/4zvSnvMHMio/hqdefault.jpg', '4zvSnvMHMio' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = '4zvSnvMHMio');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Catnip Fling Sampler', 'https://img.youtube.com/vi/Lo1s3xsWiu8/hqdefault.jpg', 'Lo1s3xsWiu8' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = 'Lo1s3xsWiu8');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Red Haired Boy', 'https://img.youtube.com/vi/BRLZ1PbJegk/hqdefault.jpg', 'BRLZ1PbJegk' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = 'BRLZ1PbJegk');
INSERT INTO video (title, thumbnail, embed_id)
SELECT 'Here Comes the Sun', 'https://img.youtube.com/vi/JN_iBLdzju8/hqdefault.jpg', 'JN_iBLdzju8' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM video WHERE embed_id = 'JN_iBLdzju8');
