
@tag
Feature: Error Validation
  I want to use this template for my feature file

  
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When  Logged In with username <name> and password <password>
    Then "Incorrect email or password." message id displayed.

   Examples: 
      | name              | password       | productName      |
      | shivam1@gmail.com |Grimreaper1!   | ZARA COAT 3      |