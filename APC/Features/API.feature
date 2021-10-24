#Author: kspramod13@gmail.com
@RestAPI @APC
Feature: Rest APIs

  @Votes
  Scenario Outline: Vote an Image Up or Down
    Given I will post using "<EndPoint>" with "<ImageID>" "<SubID>" "<Value>" to vote
    Then I validate the Status code for "<StatusCode>"
    Then I should get the success "<Message>"

    Examples: 
      | EndPoint  | StatusCode | ImageID | SubID        | Value | Message |
      | /v1/votes |        200 | asf2    | my-user-1234 |     1 | SUCCESS |
      | /v1/votes |        200 | asf2    | my-user-1235 |     0 | SUCCESS |

  @CustomerSearch
  Scenario Outline: Custome search with valid CX value
    Given I will get response using "<EndPoint>" with "<CX>" "<SearchKeyword>"
    Then I validate the Status code for "<StatusCode>"
    Then I validate the content type "<ContentType>"
    Then "<SearchKeyword>" should be in the response

    Examples: 
      | EndPoint         | StatusCode | CX                                | SearchKeyword | ContentType      |
      | /customsearch/v1 |        200 | 017576662512468239146:omuauf_lfve | Automation    | application/json |

  @CustomerSearchInvalid
  Scenario Outline: Custome search with invalid CX value
    Given I will get response using "<EndPoint>" with "<CX>" "<SearchKeyword>"
    Then I validate the Status code for "<StatusCode>"
    Then I validate the error "<Message>"
    Then I validate the "<Status>"

    Examples: 
      | EndPoint         | StatusCode | CX                                  | Message                         | Status    |
      | /customsearch/v1 |        404 | 017576662512468239146:omuauf_lfve34 | Requested entity was not found. | NOT_FOUND |
