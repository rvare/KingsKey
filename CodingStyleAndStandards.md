*Note:* This document is still under development.

The following are the coding style rules and coding standards used for this project.

# Programming Style Rules

1. Camel case is used for variables, functions, and class names.
2. Classes will start with a capital letter, while variables and functions start with a lower case letter.
3. Opening curly braces on the same line for all methods and control structures.
    1. For `if-else` statements, `else` on new line.
    2. Use a space for the opening brace.
4. For switch statemens, each case will have their own lines. (See example below.)
5. Use nouns for classes and variables, and verbs for methods.
6. For comments, when you need to say a lot, use block comments, else use single line comments for one liners.
7. For access modifiers, exclicitly state them.
8. For for-loops, the iterating varaible, try to make a name, otherwise, use `i`, `j`, etc., is okay. Or just make a comment.
9. Use spacing between operators.
    1. *Exception:* Inside function parameters.
10. For function parameters, use a space after each comma.
11. Within methods, use logical grouping.
12. Standard Java packages are first, then user created packages with space between them.

## Examples

### Switch Statements

```java
switch(x) {
    case 1:
        answer = "yes";
        break;
    case 2:
        answer = "no";
        break;
    default:
        answer = "I don't know?";
        break;
}
```

### Methods

```java
// Example of arguments in a method
someClass.someMethod(arg1, arg2, arg3+arg4);
```

```java
// Example of braces
public void someMethod() {
    // Statements
}
```

### For-Loops

Traditional:

```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```

For-each loop:

```java
for (String line : lines) {
    System.out.println(line);
}
```

