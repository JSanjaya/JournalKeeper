# An Electronic Journal

## The Who, What, and Why

I want to create a journal that keeps entries a
person's entries privately and securely. The 
application will allow a person to fill in the
date in their journal with their entry. The
application should allow someone to add, remove, and
edit any entries they want. There also should be a 
password to unlock the Journal so that unwanted eyes
can't see it. Since its such a generic application,
almost anyone can use it to record anything they want,
such as their personal thoughts or research notes.
Hence, this is a tool for anyone who wants to record
their thoughts/findings digitally and securely. One
could argue that you could just record your entries on
a simple notes app, so I want my project to go beyond
a simple text file and look more like a virtual
notebook that you can edit and manipulate digitally.
Traditional paper journals can be annoying sometimes
because you can't edit or delete entries without 
having to make some ugly scribbles, so I guess
this application will help save on paper.

This project is of interest to me because I had a
journal as a child, and I would bring it to school
to write down my thoughts. This comforted me because
I didn't really have many friends back in
elementary/middle school, so writing down my thoughts
was a replacement for not really having anyone to talk
to. One day, a classmate stole my journal and told
everyone about it. People bullied for having one, 
so I also want to make this a secure application
that only the user can view. This way, people
won't be able to invade someone's privacy as
easily, and hopefully this application will
help prevent people like me from being bullied.
Sorry for the sob story.

**User Stories**:
- As a user, I want to be able to add an entry
- As a user, I want to be able to edit an entry
- As a user, I want to be able to delete an entry
- As a user, I want to see the number of entries I 
have
- As a user, I want their to be a password system so
only I can view it.
- As a user, I want save my journal entries.
- As a user, I my old journal entries to be loaded in automatically if I ever return to the application.

**Phase 4: Task 2**
I chose to add an InvalidDateException to my Date class, so
whenever the user adds a day greater than 31 or less than 1,
a month less than 1 or greater than 12, or a year less than 2020,
the exception is thrown. This allows for robustness in the
Date class because a date should not be outside these bounds.

The Date classes' constructor, setDay, setMonth, and setYear methods
throw the exception if their parameters don't follow their bounds as
aforementioned.

The exception is caught in the JournalKeeper class, specifically
in the EntryListener class' action performed method, where if the
user clicks the "Add" button on an invalid date entry, it will appear
in the GUI but will not be saved even if the user clicks Save. An 
error message will also be displayed in the console to warn the user,
but for future implementation a JLabel displaying the message should
be added. This way, invalid dates cannot be saved into
the system and the user won't need to have access to the console.

**Phase 4: Task 3**
- After looking at the UML Diagram, I would definitely have 
tried to have higher Cohesion in my JournalKeeper class by refactoring
certain functions into separate classes, since it has so many associations
with different classes, it means its taking on a lot of responsibilities.
- I could have refactored the password GUI into its own class
instead of implementing it into main.
- I have some semantic coupling in the way I display and concatenate
my Journal entries. Since I have to display my entries in the 
JournalKeeper and Journal class, I should have created a method
that would decrease that coupling.