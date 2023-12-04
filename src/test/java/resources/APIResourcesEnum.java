package resources;

public enum APIResourcesEnum {

	AddPlaceAPI("/maps/api/place/add/json"), GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;

	APIResourcesEnum(String resource) {
		this.resource = resource;

	}

	// Now to get the value of the resource from the above methods we need to create
	// another method which will return the resource.
	
	public String getResource() {
		return resource;
		
	}

}
