# Code Refactoring

## Clean Code

The main purpose of refactoring is to fight **technical debt**. It transforms a mess into clean code and simple design.

Here are some of its features:

- Clean code is obvious for other programmers.
- Clean code doesn’t contain duplication.
- Clean code contains a minimal number of classes and other moving parts.
- Clean code passes all tests.
- Clean code is easier and cheaper to maintain!

## Causes of Technical Debt

- Business pressure
- Lack of understanding of the consequences of Technical Debt
- Failing to combat the strict coherence of the components
- Lack of Tests
- Lack of Docs
- Lack of interaction between the team members
- Long-term simultaneous development in several branches
- Delayed refactoring
- Lack of compliance monitoring
- Incompetence

## When to refactor ?

### Rule of Three

When you have to write the same code three times, it’s time to refactor.

### When adding a feature

If you have to deal with someone's dirty code, you should consider refactoring it. This makes the code clean. It will be easier to add a new feature to clean code. It also makes it easier for anyone in the future.

### When fixing a bug

Bugs live in the darkest and dirtiest places in your code. Clean them and the errors will practically discover themselves.

### During a code review

It's the last chance to tidy up the code before it goes public. Reviewing in pair with the author will make it faster to fix small bugs and gauge the time for fixing the bigger ones.

## Checklist of refactoring done the _right way_

### The code should become cleaner

If the code remains the same, then there might be reasons it is the way it is. This might happen when:

- Moving away from refactoring small changes to mixing a lot of refactoring into one big change.
- Working with sloppy code. The code was a disaster to begin with.

It is time to consider rewriting the code here.

### New functionality shouldn't be created during refactoring

Don't mix refactoring and developing new features. Atleast separate them within the confines of individual commits.

### All existing tests should pass

If they don't, you:

- made an error during the refactoring
- tests are too low level

In this case, you should consider rewriting the tests.

---

# Code Smells

# Bloaters

Bloaters are code that has grown too large that they become hard to work with. They don't begin that way, but they grow over time.

## Long Method

### Signs and Symptoms:

- A method with too many lines of code.
- Usually more than 10 lines.

### Reason for the Problem:

- Adding code to an existing method is easier than creating a new one.
- This can result in bloated methods with tangled code, also known as **spaghetti code**.

### Treatment:

If there is a need to comment on something in the method, it is a sign that that part could be extracted into a separate method.

- To reduce length use **Extract Method**.
- If local variables and parameters interfere, use **Replace Temp with Query**, **`Introduce Parameter Object`**, or **`Preserve Whole Object`**.
- If these don't work, use **Replace Method with Method Object**.

### Payoff:

- Classes with short methods are easier to understand and maintain.
- Long methods may hide bugs.

## Large Class

### Signs and Symptoms:

- A class contains many fields and methods.

### Reason for the Problem:

- They start out small, but grow over time.
- Adding code to an existing class is easier than creating a new one.

### Treatment:

- If part of the class behavior can be spun off into a separate class, use **Extract Class**.
- If part of the class behavior can be implemented in different ways or is used in rare cases, use **Extract Subclass**.
- If it's necessary to have a list of operations and behaviors the client can use, use **Extract Interface**.

### Payoff:

- Spares developers from having to remember large number of fields and methods.
- Avoids duplication of code and functionality.

## Primitive Obsession

### Signs and Symptoms:

- Using primitive types instead of objects for simple tasks.
- Using constants for coding information.

### Reason for the Problem:

- Primitive obsession occurs when primitive data types are used instead of creating new classes.
- This can result in large and unwieldy classes.
- Primitives can be used to simulate types or fields, leading to the spread of constants and poor use of arrays.

### Treatment:

- Group primitive fields into their own class and move associated behavior using `Replace Data Value with Object`.
- Use `Introduce Parameter Object` or `Preserve Whole Object` for primitive fields used in method parameters.
- Replace complicated data coded in variables with `Replace Type Code with Class`, `Replace Type Code with Subclasses` or `Replace Type Code with State/Strategy`.
- Use `Replace Array with Object` for arrays among the variables.

### Payoff:

- More flexible because of use of objects.
- Better organized code.
- Easier to find duplicate code.

## Long Parameter List

### Signs and Symptoms:

- A method with more than 3 or 4 parameters.

### Reason for the Problem:

- Long parameter lists can occur when multiple algorithms are merged into one method or when efforts are made to decrease dependency between classes.
- These lists can be hard to understand and use.
- Instead, a method can use its own object’s data or pass another object as a parameter.

### Treatment:

- Use `Replace Parameter with Method Call` for arguments that are results of method calls of another object.
- Pass the object itself to the method using `Preserve Whole Object` instead of passing a group of data received from it.
- Merge several unrelated data elements into a single parameter object using `Introduce Parameter Object`.

### Payoff:

- More readable, shorter code.
- May reveal duplicate code.

### When to ignore:

- If getting rid of parameters would cause unwanted dependencies between classes.

## Data Clumps

### Signs and Symptoms:

- A group of fields that are always passed together.

### Reason for the Problem:

- Data groups are due to poor program structure or "copypasta programming”.
- If you want to make sure whether or not some data is a data clump, just delete one of the data values and see whether the other values still make sense. If this isn’t the case, this is a good sign that this group of variables should be combined into an object.

### Treatment:

- If repeating data comprises the fields of a class, use `Extract Class` to move the fields to their own class.

- If the same data clumps are passed in the parameters of methods, use `Introduce Parameter Object` to set them off as a class.

- If some of the data is passed to other methods, think about passing the entire data object to the method instead of just individual fields. `Preserve Whole Object` will help with this.

- Look at the code used by these fields. It may be a good idea to move this code to a data class.

### Payoff:

- Improved organization of code.
- Reduce code size.

# Object-Orientation Abusers

## Switch Statements

### Signs and Symptoms:

You have a complex `switch` operator or sequence of `if` statements.

### Reason for the Problem:

- Rare use of switch and case operators is one of the hallmarks of object-oriented code.
- Often code for a single switch can be scattered in different places in the program.
- When a new condition is added, you have to find all the switch code and modify it.

As a rule of thumb, when you see switch you should think of `polymorphism`.

### Treatment:

- Isolate switch and put it in the right class using `Extract Method` and `Move Method`.
- Use `Replace Type Code with Subclasses` or `Replace Type Code with State/Strategy` for switches based on type code.
- Use `Replace Conditional with Polymorphism` after specifying the inheritance structure.
- Use `Replace Parameter with Explicit Methods` for conditions calling the same method with different parameters.
- Use `Introduce Null Object` for conditional options that are null.

### Payoff:

- Improved organization of code.

## Temporary Field

### Signs and Symptoms:

- Temporary fields get their values only under certain circumstances.
- Outside of these circumstances, they’re empty.

### Reason for the Problem:

- Temporary fields are often created for use in algorithms that require many inputs.
- These fields are used only in the algorithm and remain unused the rest of the time.
- This can make the code difficult to understand.

### Treatment:

- Put temporary fields and related code in a separate class using `Extract Class`.
- Create a method object using `Replace Method with Method Object` to achieve the same result.
- Use `Introduce Null Object` to replace conditional code checking temporary field values for existence.

### Payoff:

- Improved organization and clarity of code.

## Refused Bequest

### Signs and Symptoms:

- If a subclass uses only some of the methods and properties inherited from its parents, the hierarchy is off-kilter.
- The unneeded methods may simply go unused or be redefined and give off exceptions.

### Reason for the Problem:

Someone was motivated to create inheritance between classes only by the desire to reuse the code in a superclass. But the superclass and subclass are completely different.

### Treatment:

- Use `Replace Inheritance with Delegation` if inheritance is not appropriate and the subclass has nothing in common with the superclass.
- If inheritance is appropriate, get rid of unneeded fields and methods in the subclass. Extract all fields and methods needed by the subclass from the parent class, put them in a new superclass, and set both classes to inherit from it (`Extract Superclass`).

### Payoff:

- Improved organization of code.

## Alternative Classes with Different Interfaces

### Signs and Symptoms:

- Two classes perform identical functions but have different method names.

### Reason for the Problem:

- The programmer who created one of the classes probably didn’t know that a functionally equivalent class already existed.

### Treatment:

Try to put the interface of classes in terms of a common denominator:

- `Rename Methods` to make them identical in all alternative classes.
- `Move Method`, `Add Parameter` and `Parameterize Method` to make the signature and implementation of methods the same.
- If only part of the functionality of the classes is duplicated, try using `Extract Superclass`. In this case, the existing classes will become subclasses.
- After you have determined which treatment method to use and implemented it, you may be able to delete one of the classes.

### Payoff:

- Get rid of duplicate code.
- More readable and understandable code.

### When to ignore:

Sometimes merging classes is impossible or so difficult as to be pointless. One example is when the alternative classes are in different libraries that each have their own version of the class.

# Change Preventers

## Divergent Change

### Signs and Symptoms:

You find yourself having to change many unrelated methods when you make changes to a class. For example, when adding a new product type you have to change the methods for finding, displaying, and ordering products.

### Reason for the Problem:

Often these divergent modifications are due to poor program structure or "copypasta programming”.

### Treatment:

- Split up the behavior of the class via `Extract Class`.

- If different classes have the same behavior, you may want to combine the classes through inheritance (`Extract Superclass` and `Extract Subclass`).

### Payoff:

- Improved organization of code.
- Reduced code duplication.
- Simplified support.

## Shotgun Surgery

### Signs and Symptoms:

Making any modifications requires that you make many small changes to many different classes.

### Reason for the Problem:

- A single responsibility has been split up among a large number of classes.
- This can happen after overzealous application of `Divergent Change`.

### Treatment:

- Use `Move Method` and `Move Field` to move existing class behaviors into a single class. If there’s no class appropriate for this, create a new one.
- If moving code to the same class leaves the original classes almost empty, try to get rid of these now-redundant classes via `Inline Class`.

### Payoff:

- Improved organization of code.
- Reduced code duplication.
- Easier maintenance.

## Parallel Inheritance Hierarchies

### Signs and Symptoms:

Whenever you create a subclass for a class, you find yourself needing to create a subclass for another class.

### Reason for the Problem:

All was well as long as the hierarchy stayed small. But with new classes being added, making changes has become harder and harder.

### Treatment:

- Make instances of one hierarchy refer to instances of another hierarchy.
- Remove the hierarchy in the referred class using `Move Method` and `Move Field`.

### Payoff:

- Improved organization of code.
- Reduced code duplication.

# Dispensables

## Comments

### Signs and Symptoms:

A method is filled with explanatory comments.

### Reason for the Problem:

- Comments are usually created with the best of intentions, when the author realizes that his or her code isn’t intuitive or obvious.
- In such cases, comments are like a deodorant masking the smell of fishy code that could be improved.

### Treatment:

- If a comment is intended to explain a complex expression, the expression should be split into understandable subexpressions using `Extract Variable`.

- If a comment explains a section of code, this section can be turned into a separate method via `Extract Method`. The name of the new method can be taken from the comment text itself, most likely.

- If a method has already been extracted, but comments are still necessary to explain what the method does, give the method a self-explanatory name. Use `Rename Method` for this.

- If you need to assert rules about a state that’s necessary for the system to work, use `Introduce Assertion`.

### Payoff:

- Code becomes more intuitive and obvious.

## Duplicate Code

### Signs and Symptoms:

Two code fragments are almost identical.

### Reason for the Problem:

- Duplication can occur when multiple programmers work on the same program or when code performs the same job but looks different.
- It can also be purposeful when deadlines are tight or the programmer is unwilling to de-clutter.

### Treatment:

- Subclasses of same level

  - Use `Extract Method` for both classes, followed by `Pull Up Field` for the fields used in the method that you’re pulling up.
  - If the duplicate code is inside a constructor, use `Pull Up Constructor Body`.

- Two different classes

  - If the classes aren’t part of a hierarchy, use `Extract Superclass` in order to create a single superclass for these classes that maintains all the previous functionality.
  - If it’s difficult or impossible to create a superclass, use `Extract Class` in one class and use the new component in the other.

- Merge multiple conditional expressions with the same code using `Consolidate Conditional Expression`.
- Use `Extract Method` to place the condition in a separate method with an easy-to-understand name.
- Place identical code outside of the condition tree using `Consolidate Duplicate Conditional Fragments`.

### Payoff:

- Merging duplicate code simplifies the structure of your code and makes it shorter.

- Simplification + shortness = code that’s easier to simplify and cheaper to support.

### When to ignore:

- Sometimes merging two identical fragments of code makes the code less intiutive.

## Lazy Class

### Signs and Symptoms:

Understanding and maintaining classes always costs time and money. So if a class doesn’t do enough to earn your attention, it should be deleted.

### Reason for the Problem:

- Perhaps a class was designed to be fully functional but after some of the refactoring it has become ridiculously small.
- Perhaps it was designed to support future development work that never got done.

### Treatment:

- Components that are near-useless should be given the `Inline Class treatment`.
- For subclasses with few functions, try `Collapse Hierarchy`.

### Payoff:

- Reduced code size.
- Easy maintenance.

### When to ignore:

- Sometimes a class is small because it’s a placeholder for future development work.
- In this case, try to maintain a balance between clarity and simplicity.

## Data Class

### Signs and Symptoms:

- A class has only fields and getters/setters for these fields.
- These are simply containers for data used by other classes.
- These classes don’t contain any additional functionality and can’t independently operate on the data that they own.

### Reason for the Problem:

It’s a normal thing when a newly created class contains only a few public fields (and maybe even a handful of getters/setters). But the true power of objects is that they can contain behavior types or operations on their data.

### Treatment:

- Use `Encapsulate Field` to hide public fields and require access via getters and setters only.
- Use `Encapsulate Collection` for data stored in collections.
- Use `Move Method` and `Extract Method` to migrate functionality to the data class.
- Use `Remove Setting Method` and `Hide Method` to get rid of old methods for data access.

### Payoff:

- Improved organization of code.
- Spot duplication of code.

## Dead Code

### Signs and Symptoms:

A variable, parameter, field, method or class is no longer used (usually because it’s obsolete).

### Reason for the Problem:

- When requirements for the software have changed or corrections have been made, nobody had time to clean up the old code.
- Such code could also be found in complex conditionals, when one of the branches becomes unreachable (due to error or other circumstances).

### Treatment:

The quickest way to find dead code is to use a good `IDE`.

- Delete unused code and unneeded files.

- In the case of an unnecessary class, `Inline Class` or `Collapse Hierarchy` can be applied if a subclass or superclass is used.

- To remove unneeded parameters, use `Remove Parameter`.

### Payoff:

- Reduced code size.
- Simpler support.

## Speculative Generality

### Signs and Symptoms:

There’s an unused class, method, field or parameter.

### Reasons for the Problem:

Sometimes code is created “just in case” to support anticipated future features that never get implemented. As a result, code becomes hard to understand and support.

### Treatment:

- For removing unused abstract classes, try `Collapse Hierarchy`.
- Unnecessary delegation of functionality to another class can be eliminated via `Inline Class`.
- Unused methods? Use `Inline Method` to get rid of them.
- Methods with unused parameters should be given a look with the help of `Remove Parameter`.
- Unused fields can be simply deleted.

### Payoff:

- Slimmer code.
- Easier support.

# Couplers

## Feature Envy

### Signs and Symptoms:

A method accesses the data of another object more than its own data.

### Reason for the Problem:

This smell may occur after fields are moved to a data class. If this is the case, you may want to move the operations on data to this class as well.

### Treatment:

Keep data and functions that use this data in the same place.

- Use `Move Method` if a method clearly should be moved to another place.
- Use `Extract Method` to move part of a method that accesses the data of another object.
- Place a method in the class containing most of the data used or use `Extract Method` to split it into several parts.

### Payoff:

- Less code duplication.
- Better code organization.

### When to ignore:

Sometimes behavior is purposefully kept separate from the class that holds the data. The usual advantage of this is the ability to dynamically change the behavior (see Strategy, Visitor and other patterns).

## Inappropriate Intimacy

### Signs and Symptoms:

One class uses the internal fields and methods of another class.

### Reason for the Problem:

Keep a close eye on classes that spend too much time together. Good classes should know as little about each other as possible. Such classes are easier to maintain and reuse.

### Treatment:

- The simplest solution is to use `Move Method` and `Move Field` to move parts of one class to the class in which those parts are used. But this works only if the first class truly doesn’t need these parts.

- Another solution is to use `Extract Class` and `Hide Delegate` on the class to make the code relations “official”.

- If the classes are mutually interdependent, you should use `Change Bidirectional Association to Unidirectional`.

- If this “intimacy” is between a subclass and the superclass, consider `Replace Delegation with Inheritance`.

### Payoff:

- Improved code organization.
- Simplifies support and code reuse.

## Message Chains

### Signs and Symptoms:

In code you see a series of calls resembling `$a->b()->c()->d()`

### Reason for the Problem:

- A message chain occurs when a client requests another object, that object requests yet another one, and so on.
- These chains mean that the client is dependent on navigation along the class structure.
- Any changes in these relationships require modifying the client.

### Treatment:

- To delete a message chain, use `Hide Delegate`.
- Sometimes it’s better to think of why the end object is being used. Perhaps it would make sense to use `Extract Method` for this functionality and move it to the beginning of the chain, by using `Move Method`.

### Payoff:

- Reduces dependencies between classes of a chain.
- Reduces the amount of bloated code.

### When to ignore:

Overly aggressive delegate hiding can cause code in which it’s hard to see where the functionality is actually occurring. Which is another way of saying, avoid the Middle Man smell as well.

## Middle Man

### Signs and Symptoms:

If a class performs only one action, delegating work to another class, why does it exist at all?

### Reason for the Problem:

- This smell can be the result of overzealous elimination of Message Chains.

- It can be the result of the useful work of a class being gradually moved to other classes. The class remains as an empty shell that doesn’t do anything other than delegate.

### Treatment:

If most of a method’s classes delegate to another class, Remove Middle Man is in order.

### Payoff:

Less bulky code.

### When to ignore:

- A middle man may have been added to avoid interclass dependencies.
- Some design patterns create Middle man on purpose.

# Other Smells

## Incomplete Library Class

### Signs and Symptoms:

Sooner or later, libraries stop meeting user needs. The only solution to the problem: changing the library (often impossible since the library is read-only).

### Reason for the Problem:

The author of the library hasn’t provided the features you need or has refused to implement them.

### Treatment:

- To introduce a few methods to a library class, use `Introduce Foreign Method`.
- For big changes in a class library, use `Introduce Local Extension`.

### Payoff:

- Reduces code duplication.

### When to ignore:

Extending a library can generate additional work. If the changes to the library involve changes in code.

---
