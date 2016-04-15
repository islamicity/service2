package model;

public class Instance {
	
	private String id;
	private String service_name;
	private Endpoint endpoint;
	private String ttl;
	private String status;
	private String last_heartbeat;
	private Metadata metadata;
	
	public Endpoint getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLast_heartbeat() {
		return last_heartbeat;
	}
	public void setLast_heartbeat(String last_heartbeat) {
		this.last_heartbeat = last_heartbeat;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

}
