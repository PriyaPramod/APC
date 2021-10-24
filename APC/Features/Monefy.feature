#Author: kspramod13@gmail.com
@Monefy @Mobile @APC
Feature: Dashboard Screen Validation

  @Balance
  Scenario Outline: Balances should be calculated based on Income & Expenses
    Given Dashboard screen is displayed
    And Add Income "<Income>" by selecting the category "<incomeCategory>"
    And Add Expense "<Expense>" by selecting the category "<expenseCategory>"
    Then I validate balance is calculated correctly

    Examples: 
      | incomeCategory | expenseCategory | Income | Expense |
      | Salary         | Bills           |    450 |     325 |

  @SelectInterval
  Scenario Outline: Check is user able to select the range to see the financial status between the intervals
    Given Dashboard screen is displayed
    And Add Income "<Income>" by selecting the category "<incomeCategory>"
    And Add Expense "<Expense>" by selecting the category "<expenseCategory>"
    When Select Range from calenader
    Then Validate I am able to select the Interval

    Examples: 
      | incomeCategory | expenseCategory | Income | Expense |
      | Deposits       | Car             |    537 |     265 |

  @ExportToFile
  Scenario Outline: I should be able to export data to file
    Given Dashboard screen is displayed
    When Add Income "<Income>" by selecting the category "<incomeCategory>"
    Then Add Expense "<Expense>" by selecting the category "<expenseCategory>"
    And Export Data to file
    Then I should be able to export data to file

    Examples: 
      | incomeCategory | expenseCategory | Income | Expense |
      | Savings        | Food            |    507 |     265 |

  @ChangeCurrency
  Scenario Outline: I should be able to change the currency
    Given Dashboard screen is displayed
    When Go to change Currency
    And Change the Currency to "<Currency>"
    Then Currency Should Change to "<Currency>"

    Examples: 
      | Currency  |
      | US Dollar |

  @NegativeBalance
  Scenario Outline: Calculate by entering expenses more then the income
    Given Dashboard screen is displayed
    And Add Income "<Income>" by selecting the category "<incomeCategory>"
    And Add Expense "<Expense>" by selecting the category "<expenseCategory>"
    Then I validate balance

    Examples: 
      | incomeCategory | expenseCategory | Income | Expense |
      | Salary         | Food            |    450 |     900 |
