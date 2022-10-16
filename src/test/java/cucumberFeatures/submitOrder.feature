
@tag
Feature: Title of your feature
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  
  @Regression
  Scenario Outline: Positive test on submitting the order
    Given Logged In with username <name> and password <password>
    When I add <productName> to the cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." confirmation message is displayed

    Examples: 
      | name              | password       | productName      |
      | shivam1@gmail.com |Grimreaper1@    | ZARA COAT 3      |
