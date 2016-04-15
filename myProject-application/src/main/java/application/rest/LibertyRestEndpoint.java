/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package application.rest;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Endpoint;
import model.Instance;
import model.Service;

@ApplicationPath("rest")
@Path("/")
public class LibertyRestEndpoint extends Application {
	
	private String authorizationToken = "1jl5eti7kkj9pg4eco9hhs952tn7vrctmbp43omkb39frtjo6dv1";
	private String endpointUrl = "https://servicediscovery.ng.bluemix.net";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response hello() {
        Service serviceEndpoint = callServiceDiscovery();
        String output = callService(serviceEndpoint);
        return Response.ok("{\"message\": \"Hello from Service 2\", \"output\":" + output + "}",
                MediaType.APPLICATION_JSON).build();
    }
    
    public Service callServiceDiscovery() {
        Client client = ClientBuilder.newClient();
        String url = endpointUrl + "/api/v1/services/service1";
        System.out.println("Testing " + url);
        Response response = client.target(url).request().header("Authorization", "Bearer " + authorizationToken).get();
        int responseStatus = response.getStatus();
        System.out.println("callServiceDiscovery responseStatus:" + responseStatus);
        if (responseStatus != 200) {
        	System.out.println("response is " + response.toString());
        }
        Service responseObject = response.readEntity(Service.class);
        return responseObject;
    }
    
    public String callService(Service service) {
        Instance[] instances = service.getInstances();
        String host = null;
        for(Instance instance : instances) {
            if ("UP".equals(instance.getStatus())) {
                Endpoint endpoint = instance.getEndpoint();
                host = endpoint.getValue();
                break;
            }
        }
        if (host != null) {
            Client client = ClientBuilder.newClient();
            String url = host + "/myLibertyApp/rest";
            System.out.println("Testing " + url);
            Response response = client.target(url).request().get();
            int responseStatus = response.getStatus();
            System.out.println("callServiceDiscovery responseStatus:" + responseStatus);
            if (responseStatus != 200) {
                System.out.println("response is " + response.toString());
            }
            String responseObject = response.readEntity(String.class);
            return responseObject;
        } else {
            return "{\"Error, no instances are up\"}";
        }
    }

}