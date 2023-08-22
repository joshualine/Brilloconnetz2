# Brilloconnetz Java Developer Test
Write a java application (console or Gui) that collects the following inputs:

* Username: validation required (not empty, min 4 characters)
* Email: validation required (not empty, valid email address)
* Password: validation required (not empty, strong password with at least 1 upper case, 1 special
characters: e.g., !@#$%^&*, 1 number and must be minimum of 8 characters)
* Date of Birth: validation required (not empty, should be 16 years or greater)
## A
perform these validations and return true if all validations are passed. If the validation fails, return
all the fields that failed with a message of which of the validation requirements for each of those
fields that fails e.g., Email: not empty, Password: not a strong password.
## B
Perform the validation checks in A for the 4 fields (username, email, password and date of birth)
concurrently (Note, do not use Threads, or ExecutorServices).
## C
Building on your solution on B, instead of returning true when all validations pass, write a method
to generate a signed JWT and return it.
## D
Using the token generated in C, write a method to verify the signed token, return the string
“verification pass” if it passes and “verification fails” if it does not.
## E
Write a JUnit Test case for D to check to test a valid and invalid token.