Feature: Login to Koel App
  # Describes the high-level functionality being tested — user login to the Koel application.

  Scenario:
    # A specific test case: user logs in with valid credentials.

    Given I navigate to Koel login page
    # Precondition: Open a browser and load the Koel login page using its URL (e.g., https://qa.koel.app/)

    When I enter email "sana.iftikhar@testpro.io"
    # Action step: Type the provided email into the "Email" input field.

    And I enter password "abcd1234"
    # Action step: Type the corresponding password into the "Password" field.

    And I click the Submit button
    # Action step: Click on the "Submit" or "Login" button to trigger authentication.

    Then I should logged in to the Koel app
    # Verification step: Confirm that the login was successful — usually by checking for the presence of the dashboard, user profile, or welcome message.
