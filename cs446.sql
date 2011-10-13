create table UserType (userTypeId INTEGER PRIMARY KEY, userType TEXT);

create table User (userId INTEGER PRIMARY KEY, loginId TEXT, password TEXT, userType INTEGER, displayName TEXT, description TEXT, phoneNumber TEXT, email TEXT, FOREIGN KEY(userType) REFERENCES UserType(userTypeId));

create table Category (categoryId INTEGER PRIMARY KEY, categoryName TEXT);

create table Event (eventId INTEGER PRIMARY KEY, userID INTEGER, category INTEGER, startTime DATETIME, endTime DATETIME, location TEXT, eventDescription TEXT, eventName TEXT, eventWebsite TEXT, eventVideo TEXT, eventPhoneNumber Text, eventEmail TEXT, FOREIGN KEY(userID) REFERENCES User(userId), FOREIGN KEY(category) REFERENCES Category(categoryId) );

insert into UserType(userType) values ("Administrator");
insert into UserType(userType) values ("Moderator");
insert into UserType(userType) values ("User");

insert into Category(categoryName) values ("Category1");
insert into Category(categoryName) values ("Category2");

insert into User(loginId, password, userType, displayName, description, phoneNumber, email) values ("mike", "mike", 1, "mike y", "admin mike", "5194444444", "mike@ye.com");
insert into User(loginId, password, userType, displayName, description, phoneNumber, email) values ("martin", "martin", 1, "martin l", "admin martin", "5194444445", "martin@lacombe.com");
insert into User(loginId, password, userType, displayName, description, phoneNumber, email) values ("alice", "alice", 2, "alice ", "moderator alice", "5194444442", "alice@test.com");
insert into User(loginId, password, userType, displayName, description, phoneNumber, email) values ("bob", "bob", 3, "bob", "user bob", "5194444424", "bob@test.com");

insert into Event(userID, category, startTime, endTime, location,eventDescription, eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail) values (3, 1, "2012-10-02 15:33:16", "2012-10-02 15:33:16", "location", "event1", "event1 name", "event1.com", "youtube.com/event1", "5198078888", "event1@event.com");

insert into Event(userID, category, startTime, endTime, location,eventDescription, eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail) values (3, 1, "2012-10-02 15:33:16", "2012-10-02 15:33:16", "location", "event2", "event2 name", "event2.com", "youtube.com/event2", "5198078888", "event2@event.com");

insert into Event(userID, category, startTime, endTime, location,eventDescription, eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail) values (4, 1, "2012-10-02 15:33:16", "2012-10-02 15:33:16", "location", "event3", "event3 name", "event3.com", "youtube.com/event3", "5198078888", "event3@event.com");

insert into Event(userID, category, startTime, endTime, location,eventDescription, eventName, eventWebsite, eventVideo, eventPhoneNumber, eventEmail) values (4, 1, "2012-10-02 15:33:16", "2012-10-02 15:33:16", "location", "event4", "event4 name", "event4.com", "youtube.com/event4", "5198078888", "event4@event.com");
