# KingsKey

A password manager written in Java. The program in it's current state is intended for use by one user. 

# Features

## Manage Passwords

Manage accounts and passwords with a simple and easy to use interface. The user's passwords and other data will be stored in an encrypted file for security, so make sure to remember your master password to login. 

## Easily Copy Passwords

A simple dedicated button makes it easy to copy each password to your clipboard so you don't have to worry about mistyping your passwords when logging in to your accounts. 

You can also copy other fields, like email and account name.

## Password Generation

Comes with a password generator that creates a string of random characters. The generated passwword will have a length of 16 characters and it will contain at least one uppercase letter, lowercase letter, number, and special character to make it harder to guess.

## Password Strength Indicator

If you decide not use a password generator, a password strength indicator will tell you how strong the password you decide to use is, so that you don't end up using a password that's easily cracked. The entered password will be checked against a dictionary filled with common passwords and it will let you know if there is a match. The strength of the password will be evaluated based on the length and whether it contains uppercase characters, lowercase characters, numbers, and special characters. An entropy rating will be given for the user to get a general idea of how unpredictable or un-guessable the password is. The higher the number the better it is. 
