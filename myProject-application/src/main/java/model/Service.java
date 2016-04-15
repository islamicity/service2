package model;


public class Service {
	
    private String service_name;
    private Instance[] instances;

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public Instance[] getInstances() {
		return instances;
	}

	public void setInstances(Instance[] instances) {
		this.instances = instances;
	}
    


}
