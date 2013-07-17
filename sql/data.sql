INSERT INTO `User` (Email, AuthToken, `Password`, Salt)
VALUES ('bountyhunter@dota2.com', '314f2040b0654d01b5bda16a3739fd5f', '745c71d8fb69cdc28c30ca0fcd272e076f414d6d1e104dee', 'b97c9995c3d66811074522d872512275f39b7cb6a0897734');
INSERT INTO `User` (Email, AuthToken, `Password`, Salt)
VALUES ('nyx@dota2.com', 'e0d5dd727db94d6990695c65d0db27bd', 'bc706dfacc0855c0688013534988edf0c91e110f72e59707', '3e4adcc48c67f9ba2582718223e977b9c3358ab31ee5e348');

INSERT INTO `Profile` (UserId, FirstName, LastName)
VALUES (1, 'Gondar', 'Smith');
INSERT INTO `Profile` (UserId, FirstName, LastName)
VALUES (2, 'Anub', 'Arak');

# cat, dog
INSERT INTO Category (Id, `Name`)
VALUES (1, 'Noun');
# break, build
INSERT INTO Category (Id, `Name`)
VALUES (2, 'Verb');
# lazy, honest
INSERT INTO Category (Id, `Name`)
VALUES (3, 'Adjective');
# very
INSERT INTO Category (Id, `Name`)
VALUES (4, 'Adverb');
# but
INSERT INTO Category (Id, `Name`)
VALUES (5, 'Conjunction');
# on, in, at
INSERT INTO Category (Id, `Name`)
VALUES (6, 'Preposition');
# bravo, huh
INSERT INTO Category (Id, `Name`)
VALUES (7, 'Interjection');
# I, we, you
INSERT INTO Category (Id, `Name`)
VALUES (8, 'Pronoun');

INSERT INTO Entry (Word, Meaning, CategoryId, UserId)
VALUES ('cAt', 'Pussy animal.', 1, 1);
INSERT INTO Entry (Word, Meaning, CategoryId, UserId)
VALUES ('BuILd', 'Make something from nothing e.g. make a house from wood.', 2, 1);
INSERT INTO Entry (Word, Meaning, CategoryId, UserId)
VALUES ('PrettY', 'Very beatiful.', 3, 1);
