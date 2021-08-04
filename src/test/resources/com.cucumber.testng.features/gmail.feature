Feature: As a user I want to get ability  to use menu sections
Background:
  Given The user opens Gmail website <link>

  @smoke
  Scenario Outline: button text should match hover text
    When the user login Gmail website
    When user hover inbox button
    Then the hover text is match inbox
    Examples:
    |link|
    |https://mail.google.com/|
