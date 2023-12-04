Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then The API call is success with status code 200
And "Status" in response body is "OK"
And "Scope" in response body is "App"
And verify place_Id created maps with "<name>" using "GetPlaceAPI"

Examples: 
| name   | language | address |
#| Karan | Marathi  | Suraj Water Park  |
 | Darshit | English | India |
 
 @DeletePlace @Regression
 Scenario: Verify if Delete Place API is working
 Given Delete Payload
 When user calls "DeletePlaceAPI" with "Post" http request
 Then The API call is success with status code 200
 And "Status" in response body is "OK"
 