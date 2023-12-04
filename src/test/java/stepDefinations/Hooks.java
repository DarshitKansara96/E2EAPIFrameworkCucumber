package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("DeletePlace")
	public void BeforeScenario() throws Exception {
		// write a code to get the place_id.

		StepDefination sd = new StepDefination();
		// but execute this method only when place_id is null because if user is already
		// calling Add Place scenario then there is no logic of running the hooks.
		if (StepDefination.place_id == null) {
			sd.add_place_payload_with("Abc", "anonymous", "Canada");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_with_using("Abc", "GetPlaceAPI");
		}
	}

}
