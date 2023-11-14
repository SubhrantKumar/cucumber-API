Feature: Validating Place API's

Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples:
| name     | language | address  |
|Subhrant	 | Kannada  | Bengaluru|
#|Kumar     | Odia     |Berhampur |



Scenario: Verify if delete place functionality is working 
Given DeletePlace Payload
When user calls "DeletePlaceAPI" with "POST" http request
Then the API call is success with status code 200
And "status" in response body is "OK"


