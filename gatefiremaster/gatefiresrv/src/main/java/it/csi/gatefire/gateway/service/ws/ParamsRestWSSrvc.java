package it.csi.gatefire.gateway.service.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.csi.gatefire.common.model.Result;

@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface ParamsRestWSSrvc {
	@GET
	@Path("/refreshCache")
	Result refreshCache(@QueryParam("mode") String mode);
	
	@GET
	@Path("/checkDb")
	Result checkDb();
}
