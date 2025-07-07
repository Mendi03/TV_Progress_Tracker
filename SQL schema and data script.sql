CREATE SCHEMA `progress_tracker_db` ;

use progress_tracker_db;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);
-- Add users
INSERT INTO users (username, email, password_hash) VALUES 
	('Flint', 'tv_fan@gmail.com', '123'),
    ('Boi', 'kratos@gmail.com', '678');

-- 2. TV Shows table
CREATE TABLE Shows (
    show_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT
);

-- 3. Status types lookup table
CREATE TABLE StatusTypes (
    status_id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

-- Prepopulate allowed statuses
INSERT INTO StatusTypes (code, description) VALUES
  ('not_started', 'Show has not been started'),
  ('in_progress', 'Show is currently in progress'),
  ('completed',   'Show has been completed');

-- 4. User–Show status table referencing StatusTypes
CREATE TABLE UserShowStatus (
    user_id INT NOT NULL,
    show_id INT NOT NULL,
    status_id INT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id , show_id),
    FOREIGN KEY (user_id)
        REFERENCES Users (user_id),
    FOREIGN KEY (show_id)
        REFERENCES Shows (show_id),
    FOREIGN KEY (status_id)
        REFERENCES StatusTypes (status_id)
);

INSERT INTO Shows (title, description) VALUES
  ('Breaking Bad', 'A high school chemistry teacher turned methamphetamine producer.'),
  ('Game of Thrones', 'Noble families vie for control of the Iron Throne of Westeros.'),
  ('Stranger Things', 'Kids encounter supernatural forces and secret experiments in Hawkins.'),
  ('The Office', 'A mockumentary on the lives of office employees at Dunder Mifflin.'),
  ('Friends', 'Six friends navigate life and love in New York City.'),
  ('The Crown', 'The reign of Queen Elizabeth II from her early days to modern times.'),
  ('The Mandalorian', 'A bounty hunter protects a mysterious child in the outer reaches.'),
  ('Westworld', 'A futuristic theme park populated by android hosts.'),
  ('Sherlock', 'Modern-day retelling of Sherlock Holmes'' detective adventures.'),
  ('House of Cards', 'A ruthless congressman schemes his way to the presidency.'),
  ('The Witcher', 'A monster hunter struggles to find his place in a world of magic.'),
  ('Black Mirror', 'Standalone dramas—sharp, suspenseful tales exploring techno-paranoia.'),
  ('Rick and Morty', 'An alcoholic scientist and his grandson go on interdimensional adventures.'),
  ('Ozark', 'A financial planner drags his family from Chicago to the Missouri Ozarks.'),
  ('The Boys', 'A group of vigilantes take on corrupt superheroes.'),
  ('Dexter', 'A forensic expert leads a secret life as a vigilante serial killer.'),
  ('Better Call Saul', 'The transformation of small-time lawyer Jimmy McGill into Saul Goodman.'),
  ('Narcos', 'The rise and fall of the Medellín drug cartel.'),
  ('Peaky Blinders', 'A gangster family epic set in 1919 Birmingham, England.'),
  ('Mindhunter', 'FBI agents interview imprisoned serial killers to solve open cases.'),
  ('Fargo', 'Various chronicles of deception, intrigue and murder in and around Minnesota.'),
  ('Succession', 'The Roy family fights for control of a global media empire.'),
  ('The Handmaid''s Tale', 'A woman forced into sexual servitude in a dystopian society.'),
  ('Vikings', 'The sagas of Ragnar Lothbrok, a legendary Norse hero.'),
  ('House', 'An antisocial maverick doctor who specializes in diagnostic medicine.'),
  ('True Detective', 'Seasonal anthology series of police investigations.'),
  ('Chernobyl', 'Dramatization of the 1986 nuclear accident in Soviet Ukraine.'),
  ('The Flash', 'Barry Allen becomes the fastest man alive after a freak lab accident.'),
  ('Arrow', 'A billionaire playboy becomes a bow-wielding vigilante.'),
  ('Gotham', 'The origins of Commissioner Gordon and the villains of Batman’s city.'),
  ('Star Trek: Discovery', 'New adventures in the Star Trek universe, 10 years before Kirk.'),
  ('Doctor Who', 'The Doctor, a Time Lord from Gallifrey, travels through time and space.'),
  ('Lost', 'Plane crash survivors find themselves on a mysterious island.'),
  ('Prison Break', 'A wrongfully convicted man breaks his brother out of prison.'),
  ('The X-Files', 'FBI agents Scully and Mulder investigate paranormal cases.'),
  ('24', 'Counterterrorist agent Jack Bauer races against the clock to save America.'),
  ('The Simpsons', 'Animated sitcom about the antics of the Simpson family.'),
  ('Fleabag', 'A dry-witted woman navigates London life and personal tragedy.'),
  ('The Marvelous Mrs. Maisel', 'A 1950s housewife discovers a knack for stand-up comedy.'),
  ('How I Met Your Mother', 'A man recounts to his children the events that led him to their mother.'),
  ('Brooklyn Nine-Nine', 'Comedy following detective squads in a New York precinct.'),
  ('Community', 'A diverse group of community college students form a study group.'),
  ('Arrested Development', 'The wealthy Bluth family loses their fortune.'),
  ('Broadchurch', 'Detectives investigate the death of a young boy in a small town.'),
  ('Billions', 'High finance power struggles in New York City.'),
  ('The Expanse', 'A conspiracy threatens the fragile alliance of humanity’s colonies.'),
  ('Dark', 'Time travel conspiracy unfolds in a small German town.'),
  ('Loki', 'The God of Mischief steps out of his brother’s shadow.'),
  ('The Queen''s Gambit', 'An orphaned chess prodigy rises through the ranks.'),
  ('Avatar: The Last Airbender', 'A young monk must master all four elements.'),
  ('Umbrella Academy', 'Dysfunctional family of adopted superhero siblings reunite.'),
  ('The Good Place', 'A woman wakes up in a heaven-like afterlife by mistake.');
  
-- Inserting some anime:

INSERT INTO Shows (title, description) VALUES
  ('Naruto', 'A young ninja strives to become Hokage.'),
  ('One Piece', 'A pirate crew searches for the ultimate treasure.'),
  ('Attack on Titan', 'Humanity fights giant humanoid Titans.'),
  ('Fullmetal Alchemist: Brotherhood', 'Two brothers use alchemy to restore their bodies.'),
  ('Death Note', 'A notebook grants the power to kill anyone whose name is written in it.'),
  ('Dragon Ball Z', 'Warriors defend Earth against powerful foes.'),
  ('My Hero Academia', 'A school for aspiring superheroes.'),
  ('Sword Art Online', 'Players trapped in a deadly virtual reality game.'),
  ('Demon Slayer: Kimetsu no Yaiba', 'A boy becomes a demon slayer to avenge his family.'),
  ('Jujutsu Kaisen', 'Students battle curses with cursed energy.'),
  ('Hunter x Hunter', 'A boy becomes a hunter to find his father.'),
  ('Bleach', 'A teenager gains Soul Reaper powers to protect the living world.'),
  ('Tokyo Ghoul', 'A college student becomes a half-ghoul.'),
  ('One Punch Man', 'A hero who defeats enemies with a single punch.'),
  ('Fairy Tail', 'A guild of mages goes on adventures.'),
  ('Cowboy Bebop', 'Bounty hunters travel through space.'),
  ('Neon Genesis Evangelion', 'Teenagers pilot mechs to fight angels.'),
  ('Steins;Gate', 'A group alters the timeline with a microwave phone.'),
  ('Code Geass', 'A young man gains power to control others.'),
  ('Mob Psycho 100', 'A powerful esper balances school and spirit battling.'),
  ('Haikyuu!!', 'A high school volleyball team aims for nationals.'),
  ('Your Lie in April', 'A pianist rediscovers music through a violinist.'),
  ('Re:Zero − Starting Life in Another World', 'A man repeatedly dies in a fantasy world.'),
  ('Clannad: After Story', 'A touching slice-of-life and romance continuation.'),
  ('Sword of the Stranger', 'A samurai protects a young boy pursued by assassins.'),
  ('Vinland Saga', 'A young warrior seeks revenge in Viking age.'),
  ('Black Clover', 'A boy born without magic works to become wizard king.'),
  ('Dr. Stone', 'Science rebuilds civilization after petrification.'),
  ('The Promised Neverland', 'Children uncover dark secrets of their orphanage.'),
  ('Kaguya-sama: Love is War', 'Two geniuses battle over who will confess first.'),
  ('Toradora!', 'High school students form an unlikely friendship.'),
  ('Spirited Away', 'A girl navigates a spirit world to save her parents.'),
  ('Akira', 'A biker''s psychic powers threaten Neo-Tokyo.'),
  ('Ghost in the Shell', 'A cyborg cop hunts a mysterious hacker.'),
  ('Samurai Champloo', 'Anachronistic samurai journey through Edo-era Japan.'),
  ('Monogatari Series', 'Supernatural encounters challenge a high school student.'),
  ('Mushishi', 'A wanderer studies strange creatures called Mushi.'),
  ('Ghost Stories', 'A group investigates urban legends in a haunted town.'),
  ('The Rising of the Shield Hero', 'A hero is betrayed and seeks redemption.'),
  ('That Time I Got Reincarnated as a Slime', 'A man becomes a powerful slime in another world.'),
  ('Sword Art Online Alternative Gun Gale Online', 'Players battle in a deadly game world.'),
  ('Yuri!!! on Ice', 'A figure skater revives his career with a new coach.'),
  ('Psycho-Pass', 'A futuristic society monitors mental states for crime.'),
  ('Angel Beats!', 'Students fight their fate in high school purgatory.'),
  ('Made in Abyss', 'Explorers descend into a dangerous, mysterious pit.'),
  ('Great Pretender', 'An expert con artist pulls off international heists.'),
  ('Beastars', 'Anthropomorphic animals navigate society and crime.'),
  ('Berserk', 'A mercenary''s dark journey through a brutal medieval world.'),
  ('Howl''s Moving Castle', 'A young woman is cursed and befriends a wizard.');

